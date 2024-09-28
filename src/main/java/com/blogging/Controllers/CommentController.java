package com.blogging.Controllers;

import com.blogging.Service.CommentService;
import com.blogging.Service.Impl.CommentServiceImpl;
import com.blogging.payloads.ApiResponse;
import com.blogging.payloads.CommentDto;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto dto,@PathVariable Integer postId)
    {
        return new ResponseEntity<>(commentService.createComment(dto,postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/Post/{id}")
    public ApiResponse deleteComment(@PathVariable Integer id)
    {
        commentService.deleteComment(id);
        return new ApiResponse("Deleted succesfully",true);
    }

    @GetMapping("/post/comments/")
    public ResponseEntity<List<CommentDto>>getComments()
    {
        return new ResponseEntity<List<CommentDto>>(this.commentService.getAllComments(),HttpStatus.OK);
    }
}
