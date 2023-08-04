package com.system.blog.services;

import com.system.blog.dtos.PostDTO;
import com.system.blog.dtos.PostResponse;

import java.util.List;

public interface PostService {

    public PostDTO createPost(PostDTO postDTO);
    public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);
    public PostDTO getPostById(Long Id);
    public PostDTO updatePost(PostDTO postDTO, long id);
    public void deletePost(long id);
}
