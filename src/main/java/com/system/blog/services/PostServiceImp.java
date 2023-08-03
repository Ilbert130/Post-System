package com.system.blog.services;

import com.system.blog.dtos.PostDTO;
import com.system.blog.models.Post;
import com.system.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        //Convert DTO to an entity
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post newPost = postRepository.save(post);

        //Convert entity to a DTO
        PostDTO responsePostDTO = new PostDTO();
        postDTO.setId(newPost.getId());
        postDTO.setTitle(newPost.getTitle());
        postDTO.setDescription(newPost.getDescription());
        postDTO.setContent(newPost.getContent());

        return responsePostDTO;
    }
}
