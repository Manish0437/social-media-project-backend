package com.example.SocialMediaFeed.service;

import com.example.SocialMediaFeed.model.Post;
import com.example.SocialMediaFeed.repository.PostJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostJpaRepository postJpaRepository;


    // Fetch all posts with pagination
    public Page<Post> getPosts(int page, int size) {
        return postJpaRepository.findAll(PageRequest.of(page, size));
    }

    // Get a post by ID
    public Post createPost(Post post) {
        return postJpaRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postJpaRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post updatePost(Long id, Post post) {
        // Retrieve the existing post
        Post existingPost = getPostById(id);

        // Update only the fields provided in the request body
        if (post.getUserName() != null) {
            existingPost.setUserName(post.getUserName());
        }
        if (post.getUserImage() != null) {
            existingPost.setUserImage(post.getUserImage());
        }
        if (post.getComment() != null) {
            existingPost.setComment(post.getComment());
        }
        if (post.getFiles() != null && !post.getFiles().isEmpty()) {
            existingPost.setFiles(post.getFiles());
        }
        if (post.getLikes() != null) {
            existingPost.setLikes(post.getLikes());
        }

        // Save and return the updated post
        return postJpaRepository.save(existingPost);
    }

    public void deletePost(Long id) {
        postJpaRepository.deleteById(id);
    }

    public List<Post> getPostByUserName(String userName ){
        return postJpaRepository.findByUserName(userName);
    }
}
