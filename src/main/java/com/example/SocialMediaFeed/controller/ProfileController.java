package com.example.SocialMediaFeed.controller;

import com.example.SocialMediaFeed.model.Profile;
import com.example.SocialMediaFeed.repository.ProfileJpaRepository;
import com.example.SocialMediaFeed.service.ProfileService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/profile")

//@CrossOrigin(origins = "${frontend.url}")
public class ProfileController {


    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileJpaRepository profileJpaRepository;


    @GetMapping("/all")
    public List<Profile> getAllProfiles() {
        return profileJpaRepository.findAll();
    }


    // Endpoint to get a profile by Gmail
    @GetMapping("/{gmail}")
    public Profile getProfileByGmail(@PathVariable String gmail) {
        return profileService.getProfileByGmail(gmail);
    }


    // Endpoint to create a new profile
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profileRequest) {
        try {
            Profile profile = new Profile();
            profile.setGmail(profileRequest.getGmail());
            profile.setProfileUserName(profileRequest.getProfileUserName());
            profile.setProfileBio(profileRequest.getProfileBio());
            profile.setProfileImg(profileRequest.getProfileImg());
            profile.setProfileBgImg(profileRequest.getProfileBgImg());

            Profile createdProfile = profileService.createProfile(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{gmail}")
    public Profile updateProfile(@PathVariable String gmail, @RequestBody Profile profile) {
        return profileService.updateProfile(gmail, profile);
    }


    @DeleteMapping("/{profileId}")
    public void deleteProfile(@PathVariable Long profileId) {
        profileService.deleteProfile(profileId);
    }


}
