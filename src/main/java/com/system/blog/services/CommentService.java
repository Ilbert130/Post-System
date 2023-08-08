package com.system.blog.services;

import com.system.blog.dtos.CommentDTO;

public interface CommentService {

    public CommentDTO createComment(long postId, CommentDTO commentDTO);
}
