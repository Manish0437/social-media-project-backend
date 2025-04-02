package com.example.SocialMediaFeed.repository;
import com.example.SocialMediaFeed.model.Profile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByGmail(String gmail);
}
