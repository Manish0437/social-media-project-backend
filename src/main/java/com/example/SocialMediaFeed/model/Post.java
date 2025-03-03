


package com.example.SocialMediaFeed.model;

import jakarta.persistence.*;

// import java.time.Duration;
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
    private Integer likes;

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

    // public String getTimeSinceCreated() {
    //     if (createdAt == null) {
    //         return "Unknown";
    //     }

    //     LocalDateTime now = LocalDateTime.now();
    //     Duration duration = Duration.between(createdAt, now);

    //     long seconds = duration.getSeconds();
    //     if (seconds < 60) {
    //         return seconds + " seconds ago";
    //     }

    //     long minutes = duration.toMinutes();
    //     if (minutes < 60) {
    //         return minutes + " minutes ago";
    //     }

    //     long hours = duration.toHours();
    //     if (hours < 24) {
    //         return hours + " hours ago";
    //     }

    //     long days = duration.toDays();
    //     return days + " days ago";
    // }
}