package com.system.blog.services;

import com.system.blog.dtos.PostDTO;

import java.util.List;

public interface PostService {

    public PostDTO createPost(PostDTO postDTO);
    public List<PostDTO> getAllPost();
    public PostDTO getPostById(Long Id);
    public PostDTO updatePost(PostDTO postDTO, long id);
    public void deletePost(long id);
}
