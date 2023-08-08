package com.system.blog.services;

import com.system.blog.dtos.CommentDTO;
import com.system.blog.dtos.PostDTO;
import com.system.blog.exceptions.BlogAppException;
import com.system.blog.exceptions.ResourceNotFoundException;
import com.system.blog.models.Comment;
import com.system.blog.models.Post;
import com.system.blog.repositories.CommentRepository;
import com.system.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {

        Comment comment = mapEntity(commentDTO);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(
                "Comment", "id", postId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment doesn't below to the post");
        }

        return mapDTO(comment);
    }

    //Convert entity to DTO
    private CommentDTO mapDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());

        return commentDTO;
    }

    //Convert DTO to entity
    private Comment mapEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());

        return comment;
    }
}
