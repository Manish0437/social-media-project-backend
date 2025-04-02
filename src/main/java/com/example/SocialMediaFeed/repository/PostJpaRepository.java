package com.example.SocialMediaFeed.repository;

import com.example.SocialMediaFeed.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserName(String userName);
}
