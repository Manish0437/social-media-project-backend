


package com.example.SocialMediaFeed.model;

import jakarta.persistence.*;

// import java.time.Duration;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="post")
public class Post {

    @Id
    // @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_image")
    private String userImage;
    @Column(name="username")
    private String userName;
    @Column(name="created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;
    @Column(name="comment")
    private String comment;
    
    @ElementCollection
    @CollectionTable(
        name = "post_files",
        joinColumns = @JoinColumn(name = "post_id")
    )
    @Column(name = "file")
    private List<String> files = new ArrayList<>();
    @Column(name="likes")
    private Integer likes=0;

    public Post() {
    }

    public Post(String userImage, String userName, LocalDateTime createdAt, String comment, List<String> files, Integer likes) {
        this.userImage = userImage;
        this.userName = userName;
        this.createdAt = createdAt;
        this.comment = comment;
        this.files = files;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getTimeSinceCreated() {
        if (createdAt == null) {
            return "Unknown";
        }

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(createdAt, now);

        long seconds = duration.getSeconds();
        if (seconds < 0) {
            return "Just now"; // Handle clock syncing issues
        }
        
        if (seconds < 60) {
            return seconds < 10 ? "Just now" : seconds + " seconds ago";
        }

        long minutes = duration.toMinutes();
        if (minutes < 60) {
            return minutes == 1 ? "1 minute ago" : minutes + " minutes ago";
        }
        long hours = duration.toHours();
        if (hours < 24) {
            return hours == 1 ? "1 hour ago" : hours + " hours ago";
        }

        long days = duration.toDays();
        if (days == 1) {
            return "Yesterday";
        } else if (days < 7) {
            return days + " days ago";
        } else {
            // Return the actual date for posts older than a week
            // You'll need to format this date on the frontend
            return "over a week ago";
        }
    }
}