package com.blogging.Service.Impl;

import com.blogging.Repository.CommentRepo;
import com.blogging.Repository.PostRepo;
import com.blogging.Service.CommentService;
import com.blogging.entites.Post;
import com.blogging.entites.Comment;
import com.blogging.exceptions.ResourceNotFoundException;
import com.blogging.payloads.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto dto, Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        Comment com=modelMapper.map(dto, Comment.class);
        com.setPost(post);
        return modelMapper.map(this.commentRepo.save(com),CommentDto.class);

    }

    @Override
    public CommentDto updateComment(Integer id) {
        return null;
    }


    @Override
    public List<CommentDto> getAllComments() {

        List<Comment>comments=this.commentRepo.findAll();
        List<CommentDto>res=comments.stream().map((com)->this.modelMapper.map(com,CommentDto.class)).collect(Collectors.toList());

        return res;
    }

    @Override
    public void deleteComment(Integer Id) {
        Comment com=this.commentRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("post","PostId",Id));
        this.commentRepo.delete(com);
    }
}
