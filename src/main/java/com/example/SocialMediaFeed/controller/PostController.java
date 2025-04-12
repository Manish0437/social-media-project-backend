package com.example.SocialMediaFeed.controller;

import com.example.SocialMediaFeed.model.Post;
import com.example.SocialMediaFeed.service.PostService;
import com.example.SocialMediaFeed.repository.PostJpaRepository;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api/posts")


//@CrossOrigin(origins = "${frontend.url}")
public class PostController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private PostJpaRepository postJpaRepository;

    // Endpoint to get all posts with pagination
    @GetMapping
    public Page<Post> getPosts(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        logger.info("Fetching posts with page: {}, size: {}", page, size);
        return postJpaRepository.findAll(pageable);
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        // return postService.getAllPosts();


        logger.info("Received request to get all posts");
        List<Post> posts = postService.getAllPosts();
        logger.info("Returning {} posts", posts.size());
        return posts;
    }

    // Endpoint to create a new post
//    @PostMapping
//    public ResponseEntity<Post> createPost(@RequestBody Post postRequest) {
//        try {
//            Post post = new Post();
//            post.setUserName(postRequest.getUserName());
//            post.setUserImage(postRequest.getUserImage());
//            post.setComment(postRequest.getComment());
//            post.setFiles(postRequest.getFiles());
//            post.setLikes(0);
//
//            // Parse the ISO date format from frontend or use current server time
//            LocalDateTime createdAt;
//            if (postRequest.getCreatedAt() != null) {
//                createdAt = postRequest.getCreatedAt();
//            } else {
//                createdAt = LocalDateTime.now();
//            }
//            post.setCreatedAt(createdAt);
//
//            Post savedPost = postJpaRepository.save(post);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }



















    // Endpoint to create a new post

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post postRequest) {
        try {
            Post post = new Post();
            post.setUserName(postRequest.getUserName());
            post.setUserImage(postRequest.getUserImage());
            post.setComment(postRequest.getComment());
            post.setFiles(postRequest.getFiles());
            post.setLikes(0);
            // Parse the ISO date format from frontend or use current server time
            LocalDateTime createdAt;
            if (postRequest.getCreatedAt() != null) {
                createdAt = postRequest.getCreatedAt();
            } else {
                createdAt = LocalDateTime.now();
            }
            post.setCreatedAt(createdAt);
            Post savedPost = postJpaRepository.save(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }












    // Endpoint to get a post by ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }


    // Endpoint to get posts by username
    @GetMapping("/user")
    public ResponseEntity<List<Post>> getPostsByUserName(@RequestParam String userName) {
        logger.info("Fetching posts for user: {}", userName);
        List<Post> posts = postService.getPostByUserName(userName);
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
