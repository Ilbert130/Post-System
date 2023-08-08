package com.system.blog.controllers;

import com.system.blog.dtos.CommentDTO;
import com.system.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    public CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId")long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> saveComment(@PathVariable(value = "postId")long postId,
                                                  @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }
}
