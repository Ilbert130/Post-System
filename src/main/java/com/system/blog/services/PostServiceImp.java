package com.system.blog.services;

import com.system.blog.dtos.PostDTO;
import com.system.blog.exceptions.ResourceNotFoundException;
import com.system.blog.models.Post;
import com.system.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        post = mapEntity(postDTO);

        //Saving data
        Post newPost = postRepository.save(post);

        //Convert entity to a DTO
        PostDTO responsePostDTO = new PostDTO();
        responsePostDTO = mapDTO(newPost);

        return responsePostDTO;
    }

    @Override
    public List<PostDTO> getAllPost(int pageNumber, int pageSize) {
        //Creating the pagination
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();

        return postList.stream().map(post -> mapDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatedPost = postRepository.save(post);
        return mapDTO(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    //Convert entity to DTO
    private PostDTO mapDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());

        return postDTO;
    }

    //Convert DTO to entity
    private Post mapEntity(PostDTO postDTO){
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        return post;
    }
}
