package com.example.SocialMediaFeed.controller;

import com.example.SocialMediaFeed.model.Post;
import com.example.SocialMediaFeed.service.PostService;
import com.example.SocialMediaFeed.repository.PostJpaRepository;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
import java.util.List;
// import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000")

public class PostController {
   
    private static final Logger logger = (Logger) LoggerFactory.getLogger(PostController.class);
    
    @Autowired
    private PostService postService;
    @Autowired
    private PostJpaRepository postJpaRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        // return postService.getAllPosts();


        logger.info("Received request to get all posts");
        List<Post> posts = postService.getAllPosts();
        logger.info("Returning {} posts", posts.size());
        return posts;
    }

    // @PostMapping
    // public Post createPost(@RequestBody Post post) {
    //     return postService.createPost(post);
    // }



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


    @GetMapping("/user")
    public List<Post> getPostsByUserName(@RequestParam String userName) {
        logger.info("Fetching posts for user: {}", userName);
        List<Post> posts = postService.getPostByUserName(userName);
        logger.info("Found {} posts for user {}", posts.size(), userName);
        return posts;
    }

//    @GetMapping("/{userName}")
//    public List<Post> getPostByUserName(@PathVariable String userName){
//        List<Post> posts = postService.getPostByUserName(userName);
//        return posts;
//    }
}


















































// package com.example.SocialMediaFeed.service;

// import com.example.SocialMediaFeed.model.Post;
// import com.example.SocialMediaFeed.repository.PostJpaRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
// import java.util.logging.Logger;

// @Service
// public class PostService {
//     private static final Logger logger = Logger.getLogger(PostService.class.getName());

//     @Autowired
//     private PostJpaRepository postJpaRepository;
    
//     public Post createPost(Map<String, Object> postData) {
//         Post post = new Post();
        
//         // Set basic post data
//         if (postData.containsKey("comment") && postData.get("comment") instanceof String) {
//             post.setComment((String) postData.get("comment"));
//         }
        
//         // Set default values
//         post.setCreatedAt(LocalDateTime.now());
//         post.setUserName("Unknown"); // Default username or get from authentication
//         post.setUserImage("default-avatar.jpg"); // Default user image
//         post.setLikes(0); // Initial likes count
        
//         // Initialize files list if null
//         if (post.getFiles() == null) {
//             post.setFiles(new ArrayList<>());
//         }
        
//         // Combine all file lists into one
//         List<String> allFiles = new ArrayList<>();
        
//         // Process various file lists
//         processFileList(postData, "fileList", allFiles);
//         processFileList(postData, "cameraList", allFiles);
//         processFileList(postData, "photoList", allFiles);
//         processFileList(postData, "videoList", allFiles);
        
//         // Set the combined files list
//         post.setFiles(allFiles);
        
//         return postJpaRepository.save(post);
//     }
    
//     @SuppressWarnings("unchecked")
//     private void processFileList(Map<String, Object> postData, String listKey, List<String> allFiles) {
//         if (postData.containsKey(listKey)) {
//             try {
//                 Object fileListObj = postData.get(listKey);
                
//                 if (fileListObj instanceof List<?>) {
//                     List<?> fileList = (List<?>) fileListObj;
                    
//                     for (Object fileObj : fileList) {
//                         if (fileObj instanceof Map) {
//                             Map<String, Object> fileMap = (Map<String, Object>) fileObj;
                            
//                             if (fileMap.containsKey("name") && fileMap.get("name") instanceof String) {
//                                 allFiles.add((String) fileMap.get("name"));
//                             }
//                         } else if (fileObj instanceof String) {
//                             // In case simple strings are used instead of objects
//                             allFiles.add((String) fileObj);
//                         }
//                     }
//                 } else {
//                     logger.warning(listKey + " is not a List");
//                 }
//             } catch (ClassCastException e) {
//                 logger.warning("Error processing " + listKey + ": " + e.getMessage());
//             }
//         }
//     }

//     public List<Post> getAllPosts() {
//         return postJpaRepository.findAll();
//     }

//     public Post getPostById(Long id) {
//         return postJpaRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Post not found"));
//     }

//     public Post updatePost(Long id, Post post) {
//         // Retrieve the existing post
//         Post existingPost = getPostById(id);

//         // Update only the fields provided in the request body
//         if (post.getUserName() != null) {
//             existingPost.setUserName(post.getUserName());
//         }
//         if (post.getUserImage() != null) {
//             existingPost.setUserImage(post.getUserImage());
//         }
//         if (post.getComment() != null) {
//             existingPost.setComment(post.getComment());
//         }
//         if (post.getFiles() != null && !post.getFiles().isEmpty()) {
//             existingPost.setFiles(post.getFiles());
//         }
//         if (post.getLikes() != null) {
//             existingPost.setLikes(post.getLikes());
//         }

//         // Save and return the updated post
//         return postJpaRepository.save(existingPost);
//     }

//     public void deletePost(Long id) {
//         postJpaRepository.deleteById(id);
//     }
// }










































































































































































// package com.example.SocialMediaFeed.controller;

// import com.example.SocialMediaFeed.model.Post;
// import com.example.SocialMediaFeed.service.PostService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/posts")
// @CrossOrigin(origins = "*") // Allow requests from any origin for testing
// public class PostController {

//     private final PostService postService;

//     @Autowired
//     public PostController(PostService postService) {
//         this.postService = postService;
//     }

//     @GetMapping
//     public ResponseEntity<List<Post>> getAllPosts() {
//         return ResponseEntity.ok(postService.getAllPosts());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Post> getPostById(@PathVariable Long id) {
//         return postService.getPostById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public ResponseEntity<Post> createPost(@RequestBody Map<String, Object> postData) {
//         // Use the special method that handles the complex request structure
//         Post createdPost = postService.createPostFromMap(postData);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
//         try {
//             Post updatedPost = postService.updatePost(id, post);
//             return ResponseEntity.ok(updatedPost);
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deletePost(@PathVariable Long id) {
//         postService.deletePost(id);
//         return ResponseEntity.noContent().build();
//     }
// }