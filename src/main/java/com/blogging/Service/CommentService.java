package com.blogging.Service;

import com.blogging.payloads.CommentDto;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer id);
    CommentDto updateComment(Integer id);
//    CommentDto getComment(Integer Id);
    List<CommentDto> getAllComments();

    void deleteComment(Integer PostId);

}
