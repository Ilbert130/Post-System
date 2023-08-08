package com.system.blog.services;

import com.system.blog.dtos.CommentDTO;

import java.util.List;

public interface CommentService {

    public CommentDTO createComment(long postId, CommentDTO commentDTO);
    public List<CommentDTO> getCommentsByPostId(long postId);
    public CommentDTO getCommentById(long postId, long commentId);
}
