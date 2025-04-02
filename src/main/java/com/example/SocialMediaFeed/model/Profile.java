package com.example.SocialMediaFeed.model;
import jakarta.persistence.*;




@Entity
@Table(name="profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    @Column(name="gmail")
    private String gmail;
    @Column(name="profile_user_name")
    private String profileUserName;
    @Column(name="profile_bio")
    private String profileBio;
    @Column(name="profile_img")
    private String profileImg;
    @Column(name="profile_bg_img")
    private String profileBgImg;

    public Profile(){

    }

    public Profile(String gmail,String profileUserName,String profileBio,String profileImg,String profileBgImg){
        this.gmail=gmail;
        this.profileUserName=profileUserName;
        this.profileBio=profileBio;
        this.profileBgImg=profileBgImg;
    }

    public Long getprofileId() {
        return profileId;
    }

    public void setprofileId(Long profileId){
        this.profileId=profileId;
    }

    public String getGmail(){
        return gmail;
    }

    public void setGmail(String gmail){
        this.gmail=gmail;
    }


    public String getProfileUserName(){return profileUserName;}

    public void setProfileUserName(String profileUserName){
        this.profileUserName=profileUserName;
    }

    public String getProfileBio(){return profileBio;}

    public void setProfileBio(String profileBio){
        this.profileBio=profileBio;
    }

    public String getProfileImg(){return profileImg;}

    public void setProfileImg(String profileImg){
        this.profileImg=profileImg;
    }

    public String getProfileBgImg(){return profileBgImg;}

    public void setProfileBgImg(String profileBgImg){
        this.profileBgImg=profileBgImg;
    }

}
