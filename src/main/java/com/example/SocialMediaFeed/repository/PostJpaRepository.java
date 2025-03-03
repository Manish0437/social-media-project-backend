package com.example.SocialMediaFeed.repository;

import com.example.SocialMediaFeed.model.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserName(String userName);
}