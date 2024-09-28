package com.blogging.Service;

import com.blogging.entites.Category;
import com.blogging.entites.Post;
import com.blogging.entites.users;
import com.blogging.payloads.PostDto;
import com.blogging.payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto post,Integer userId,Integer catId);
    PostDto updatePost( PostDto user,Integer  PostId);
    PostDto getPostById(Integer userId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    void deletePost(Integer PostId);

    List<PostDto>getByUser(Integer userId);
    List<PostDto>getByCategory(Integer catId);
    List<PostDto>searchPosts(String keyword);

}
