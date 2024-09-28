package com.blogging.Controllers;

import com.blogging.Service.FileService;
import com.blogging.Service.PostService;
import com.blogging.payloads.ApiResponse;
import com.blogging.payloads.PostDto;
import com.blogging.payloads.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    //creating a post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto>createpost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId)
    {
        PostDto createdpost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createdpost, HttpStatus.CREATED);
    }
    //getting posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>>getByUser(@PathVariable Integer userId)
    {
        return new ResponseEntity<List<PostDto>>(this.postService.getByUser(userId),HttpStatus.OK);
    }
    //getting posts by cat
    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDto>>getByCategory(@PathVariable Integer catId)
    {
        return new ResponseEntity<List<PostDto>>(this.postService.getByCategory(catId),HttpStatus.OK);
    }

    //deleting a post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ApiResponse("Successfully deleted...!!",true);
    }

    //getting all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse>getAllPosts(@RequestParam(value="pageNumber",defaultValue = "0",required = false) Integer pageNumber, @RequestParam(value="pageSize",defaultValue = "10  ",required = false)Integer pageSize,
    @RequestParam(value="sortBy",defaultValue = "postId",required = false)String sortBy,@RequestParam(value="sortDir",defaultValue = " asc",required = false)String sortDir)
    {
        return new ResponseEntity<PostResponse>(this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir),HttpStatus.FOUND);
    }
    //getpost by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId)
    {
        return new ResponseEntity<PostDto>(postService.getPostById(postId),HttpStatus.FOUND);
    }
    //updating a post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto dto,@PathVariable Integer postId)
    {
        return new ResponseEntity<PostDto>(postService.updatePost(dto,postId),HttpStatus.CREATED);
    }

    @GetMapping("posts/search/keyword")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keyword)
    {
        return new ResponseEntity<List<PostDto>>(this.postService.searchPosts(keyword),HttpStatus.OK);
    }
    // post image upload

    public ResponseEntity<PostDto>uploadImage(
            @RequestParam("image")MultipartFile img,
            @PathVariable Integer postId
            )throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName=this.fileService.uploadImage(path,img);
        postDto.setImageName(fileName);
        PostDto updatedImage=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedImage,HttpStatus.OK);
    }

}
