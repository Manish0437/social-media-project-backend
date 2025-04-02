package com.example.SocialMediaFeed.service;

import com.example.SocialMediaFeed.model.Profile;
import com.example.SocialMediaFeed.repository.ProfileJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    // Fetch all profiles
    public List<Profile> getAllProfiles() {
        return profileJpaRepository.findAll();
    }

    // Fetch a profile by Gmail
    public Profile getProfileByGmail(String gmail) {
        return profileJpaRepository.findByGmail(gmail)
                .orElseThrow(() -> new RuntimeException("Profile not found with Gmail: " + gmail));
    }

    // Create a new profile
    public Profile createProfile(Profile profile) {
        return profileJpaRepository.save(profile);
    }

    // Update an existing profile
    public Profile updateProfile(String gmail, Profile profile) {
        Profile existingProfile = profileJpaRepository.findByGmail(gmail)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + gmail));
        if(profile.getProfileUserName() != null) {
            existingProfile.setProfileUserName(profile.getProfileUserName());
        }
        if(profile.getProfileBio() != null) {
            existingProfile.setProfileBio(profile.getProfileBio());
        }
        if(profile.getProfileImg() != null) {
            existingProfile.setProfileImg(profile.getProfileImg());
        }
        if(profile.getProfileBgImg() != null) {
            existingProfile.setProfileBgImg(profile.getProfileBgImg());
        }
        return profileJpaRepository.save(existingProfile);
    }

    public void deleteProfile(Long id) {
        profileJpaRepository.deleteById(id);
    }

}
