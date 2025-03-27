// package com.example.SocialMediaFeed.service;

// import com.example.SocialMediaFeed.model.Post;
// import com.example.SocialMediaFeed.repository.PostJpaRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class PostService {
//     @Autowired
//     private PostJpaRepository postJpaRepository;

//     public Post createPost(Post post) {
//         if (post.getCreatedAt() == null) {
//         post.setCreatedAt(LocalDateTime.now());
//         }
//         if (post.getImages() == null) {
//             post.setImages(new ArrayList<>());
//         }
//     return postJpaRepository.save(post);
//     }

//     public List<Post> getAllPosts() {
//         return postJpaRepository.findAll();
//     }

//     public Post getPostById(Long id) {
//         return postJpaRepository.findById(id)
//             .orElseThrow(() -> new RuntimeException("Post not found"));
//     }

//     public Post updatePost(Long id, Post post) {
//         Post existingPost = getPostById(id);
//         existingPost.setComment(post.getComment());
//         existingPost.setImages(post.getImages());
//         existingPost.setLikes(post.getLikes());
//         return postJpaRepository.save(existingPost);
//     }

//     public void deletePost(Long id) {
//         postJpaRepository.deleteById(id);
//     }
// }




package com.example.SocialMediaFeed.service;

import com.example.SocialMediaFeed.model.Post;
import com.example.SocialMediaFeed.repository.PostJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostJpaRepository postJpaRepository;

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



































































































// package com.example.SocialMediaFeed.service;

// import com.example.SocialMediaFeed.model.Post;
// import com.example.SocialMediaFeed.repository.PostJpaRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// @Service
// public class PostService {
//     @Autowired
//     private PostJpaRepository postJpaRepository;
    
//     public Post createPost(Map<String, Object> postData) {
//         Post post = new Post();
        
//         // Set basic post data
//         if (postData.containsKey("comment")) {
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
        
//         // Process fileList
//         if (postData.containsKey("fileList")) {
//             List<Map<String, Object>> fileList = (List<Map<String, Object>>) postData.get("fileList");
//             for (Map<String, Object> file : fileList) {
//                 if (file.containsKey("name")) {
//                     allFiles.add((String) file.get("name"));
//                 }
//             }
//         }
        
//         // Process cameraList
//         if (postData.containsKey("cameraList")) {
//             List<Map<String, Object>> cameraList = (List<Map<String, Object>>) postData.get("cameraList");
//             for (Map<String, Object> file : cameraList) {
//                 if (file.containsKey("name")) {
//                     allFiles.add((String) file.get("name"));
//                 }
//             }
//         }
        
//         // Process photoList
//         if (postData.containsKey("photoList")) {
//             List<Map<String, Object>> photoList = (List<Map<String, Object>>) postData.get("photoList");
//             for (Map<String, Object> file : photoList) {
//                 if (file.containsKey("name")) {
//                     allFiles.add((String) file.get("name"));
//                 }
//             }
//         }
        
//         // Process videoList
//         if (postData.containsKey("videoList")) {
//             List<Map<String, Object>> videoList = (List<Map<String, Object>>) postData.get("videoList");
//             for (Map<String, Object> file : videoList) {
//                 if (file.containsKey("name")) {
//                     allFiles.add((String) file.get("name"));
//                 }
//             }
//         }
        
//         // Set the combined files list
//         post.setFiles(allFiles);
        
//         return postJpaRepository.save(post);
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









































































































































// package com.example.SocialMediaFeed.service;

// import com.example.SocialMediaFeed.model.Post;
// import com.example.SocialMediaFeed.repository.PostJpaRepository;
// // import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class PostService {

//     private final PostJpaRepository postJpaRepository;

//     public PostService(PostJpaRepository postJpaRepository) {
//         this.postJpaRepository = postJpaRepository;
//     }

//     /**
//      * Get all posts
//      * @return List of all posts
//      */
//     public List<Post> getAllPosts() {
//         return postJpaRepository.findAll();
//     }

//     /**
//      * Get post by ID
//      * @param id Post ID
//      * @return Post if found
//      */
//     public Optional<Post> getPostById(Long id) {
//         return postJpaRepository.findById(id);
//     }

//     /**
//      * Create a new post
//      * @param post Post to create
//      * @return Created post with ID
//      */
//     @Transactional
//     public Post createPost(Post post) {
//         // Set creation time if not set
//         if (post.getCreatedAt() == null) {
//             post.setCreatedAt(LocalDateTime.now());
//         }
        
//         // Initialize likes to 0 if null
//         if (post.getLikes() == null) {
//             post.setLikes(0);
//         }

//         if(post.getFiles() != null) {
//             System.out.println(post.getFiles());
//             post.setFiles(post.getFiles());
//         }
        
//         return postJpaRepository.save(post);
//     }

//     /**
//      * Update an existing post
//      * @param id Post ID to update
//      * @param postDetails Updated post details
//      * @return Updated post
//      */
//     @Transactional
//     public Post updatePost(Long id, Post postDetails) {
//         return postJpaRepository.findById(id)
//             .map(existingPost -> {
//                 // Update fields if they're not null in the request
//                 if (postDetails.getUserName() != null) {
//                     existingPost.setUserName(postDetails.getUserName());
//                 }
                
//                 if (postDetails.getUserImage() != null) {
//                     existingPost.setUserImage(postDetails.getUserImage());
//                 }
                
//                 if (postDetails.getComment() != null) {
//                     existingPost.setComment(postDetails.getComment());
//                 }
                
//                 if (postDetails.getLikes() != null) {
//                     existingPost.setLikes(postDetails.getLikes());
//                 }
                
//                 // Update files list if provided
//                 if (postDetails.getFiles() != null && !postDetails.getFiles().isEmpty()) {
//                     existingPost.setFiles(postDetails.getFiles());
//                 }
                
//                 return postJpaRepository.save(existingPost);
//             })
//             .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
//     }

//     /**
//      * Delete a post
//      * @param id Post ID to delete
//      */
//     @Transactional
//     public void deletePost(Long id) {
//         postJpaRepository.deleteById(id);
//     }
// }






































































































































































































// package com.example.SocialMediaFeed.service;

// import com.example.SocialMediaFeed.model.Post;
// import com.example.SocialMediaFeed.repository.PostJpaRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// @Service
// public class PostService {

//     private final PostJpaRepository postJpaRepository;

//     public PostService(PostJpaRepository postJpaRepository) {
//         this.postJpaRepository = postJpaRepository;
//     }

//     /**
//      * Get all posts
//      * @return List of all posts
//      */
//     public List<Post> getAllPosts() {
//         return postJpaRepository.findAll();
//     }

//     /**
//      * Get post by ID
//      * @param id Post ID
//      * @return Post if found
//      */
//     public Optional<Post> getPostById(Long id) {
//         return postJpaRepository.findById(id);
//     }

//     /**
//      * Create a new post
//      * @param post Post to create
//      * @return Created post with ID
//      */
//     @Transactional
//     public Post createPost(Post post) {
//         System.out.println("post inside the spring:"+post.getFiles());
//         // Set creation time if not set
//         if (post.getCreatedAt() == null) {
//             post.setCreatedAt(LocalDateTime.now());
//         }
        
//         // Initialize likes to 0 if null
//         if (post.getLikes() == null) {
//             post.setLikes(0);
//         }

//         // Initialize files list if null
//         if (post.getFiles() == null) {
//             post.setFiles(new ArrayList<>());
//         }
        
//         return postJpaRepository.save(post);
//     }

//     /**
//      * Create a new post from a map containing comment and various file lists
//      * @param postData Map containing post data including comment and file lists
//      * @return Created post with ID
//      */
//     @Transactional
//     public Post createPostFromMap(Map<String, Object> postData) {
//         Post post = new Post();
        
//         // Set basic post data
//         if (postData.containsKey("comment")) {
//             post.setComment((String) postData.get("comment"));
//         }
        
//         // Set default values
//         post.setCreatedAt(LocalDateTime.now());
//         post.setUserName("Anonymous"); // Default username or get from authentication
//         post.setUserImage("default-avatar.jpg"); // Default user image
//         post.setLikes(0); // Initial likes count
        
//         // Initialize files list
//         List<String> allFiles = new ArrayList<>();
        
//         // Process fileList
//         if (postData.containsKey("fileList") && postData.get("fileList") instanceof List) {
//             List<?> fileList = (List<?>) postData.get("fileList");
//             for (Object fileObj : fileList) {
//                 if (fileObj instanceof Map) {
//                     Map<?, ?> file = (Map<?, ?>) fileObj;
//                     if (file.containsKey("name")) {
//                         allFiles.add(file.get("name").toString());
//                     }
//                 }
//             }
//         }
        
//         // Process cameraList
//         if (postData.containsKey("cameraList") && postData.get("cameraList") instanceof List) {
//             List<?> cameraList = (List<?>) postData.get("cameraList");
//             for (Object fileObj : cameraList) {
//                 if (fileObj instanceof Map) {
//                     Map<?, ?> file = (Map<?, ?>) fileObj;
//                     if (file.containsKey("name")) {
//                         allFiles.add(file.get("name").toString());
//                     }
//                 }
//             }
//         }
        
//         // Process photoList
//         if (postData.containsKey("photoList") && postData.get("photoList") instanceof List) {
//             List<?> photoList = (List<?>) postData.get("photoList");
//             for (Object fileObj : photoList) {
//                 if (fileObj instanceof Map) {
//                     Map<?, ?> file = (Map<?, ?>) fileObj;
//                     if (file.containsKey("name")) {
//                         allFiles.add(file.get("name").toString());
//                     }
//                 }
//             }
//         }
        
//         // Process videoList
//         if (postData.containsKey("videoList") && postData.get("videoList") instanceof List) {
//             List<?> videoList = (List<?>) postData.get("videoList");
//             for (Object fileObj : videoList) {
//                 if (fileObj instanceof Map) {
//                     Map<?, ?> file = (Map<?, ?>) fileObj;
//                     if (file.containsKey("name")) {
//                         allFiles.add(file.get("name").toString());
//                     }
//                 }
//             }
//         }
        
//         // Set the combined files list
//         post.setFiles(allFiles);
//         System.out.println("Combined files: " + allFiles);
        
//         return postJpaRepository.save(post);
//     }

//     /**
//      * Update an existing post
//      * @param id Post ID to update
//      * @param postDetails Updated post details
//      * @return Updated post
//      */
//     @Transactional
//     public Post updatePost(Long id, Post postDetails) {
//         return postJpaRepository.findById(id)
//             .map(existingPost -> {
//                 // Update fields if they're not null in the request
//                 if (postDetails.getUserName() != null) {
//                     existingPost.setUserName(postDetails.getUserName());
//                 }
                
//                 if (postDetails.getUserImage() != null) {
//                     existingPost.setUserImage(postDetails.getUserImage());
//                 }
                
//                 if (postDetails.getComment() != null) {
//                     existingPost.setComment(postDetails.getComment());
//                 }
                
//                 if (postDetails.getLikes() != null) {
//                     existingPost.setLikes(postDetails.getLikes());
//                 }
                
//                 // Update files list if provided
//                 if (postDetails.getFiles() != null && !postDetails.getFiles().isEmpty()) {
//                     existingPost.setFiles(postDetails.getFiles());
//                 }
                
//                 return postJpaRepository.save(existingPost);
//             })
//             .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
//     }

//     /**
//      * Delete a post
//      * @param id Post ID to delete
//      */
//     @Transactional
//     public void deletePost(Long id) {
//         postJpaRepository.deleteById(id);
//     }
// }