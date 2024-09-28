package com.blogging.Service.Impl;
import com.blogging.Repository.userRepo;
import com.blogging.Repository.CategoryRepo;
import com.blogging.Repository.PostRepo;
import com.blogging.Service.PostService;
import com.blogging.entites.Category;
import com.blogging.entites.Post;
import com.blogging.entites.users;
import com.blogging.exceptions.ResourceNotFoundException;
import com.blogging.payloads.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo repo;
    @Autowired
    private userRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto dto,Integer userId,Integer catId) {
        users user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userid",userId));
        Category cat=categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","catId",catId));

        Post pos=modelMapper.map(dto,Post.class);
        pos.setImageName("default.png");
        pos.setAddedDate(new Date());
        pos.setUser(user);
        pos.setCategory(cat);
       Post createdPost=this.repo.save(pos);
        return this.modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto dto, Integer postId) {
        Post post=this.repo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        post.setContent(dto.getContent());
        post.setTitle(dto.getTitle());
        post.setImageName(dto.getImageName());

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.repo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        System.out.println(post.toString());
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p=PageRequest.of(pageNumber,pageSize,sort);
       Page<Post> pagepost=this.repo.findAll(p);

       List<Post>posts=pagepost.getContent();
        List <PostDto>res=new ArrayList<>();
        for (Post ps:posts)
        {
            res.add(modelMapper.map(ps, PostDto.class));
        }

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(res);
        postResponse.setPageNumber(pagepost.getNumber());
        postResponse.setPageSize(pagepost.getSize());
        postResponse.setTotalElements(pagepost.getTotalElements());
        postResponse.setLastpage(pagepost.isLast());

        return postResponse;
    }

    @Override
    public void deletePost(Integer postId) {
        this.repo.deleteById(postId);


    }

    @Override
    public List<PostDto> getByUser(Integer userId) {

        users user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post>posts=this.repo.findByUser(user);
        List<PostDto>res=new ArrayList<>();
        for(Post p:posts)
        {
            res.add(modelMapper.map(p,PostDto.class));
        }

        return res;
    }

    @Override
    public List<PostDto> getByCategory(Integer catId) {
        Category cat=this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category","catId",catId));
        List<Post>posts=this.repo.findByCategory(cat);
        List<PostDto>res=new ArrayList<>();
        for(Post p:posts)
        {
            res.add(modelMapper.map(p,PostDto.class));
        }


        return res;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post>post=this.repo.searchByTitle(keyword);
        List<PostDto>postDtos=post.stream().map((post1)->this.modelMapper.map(post1, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
