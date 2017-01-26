package com.app.recall.entity.retrofit.params;

/**
 * Created by KenChan on 17/1/26.
 */

public class Platforms {
    public Platforms(String name, String username, String token) {
        this.name = name;
        this.username = username;
        this.token = token;
    }

    /**
     * name : 1
     * openId : 1
     * token : 1
     * profilePhoto : 1
     * username : 1
     * nickname : 1
     */
    private String name;
    private String openId;
    private String token;
    private String profilePhoto;
    private String username;
    private String nickname;

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public String getOpenId() { return openId;}

    public void setOpenId(String openId) { this.openId = openId;}

    public String getToken() { return token;}

    public void setToken(String token) { this.token = token;}

    public String getProfilePhoto() { return profilePhoto;}

    public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto;}

    public String getUsername() { return username;}

    public void setUsername(String username) { this.username = username;}

    public String getNickname() { return nickname;}

    public void setNickname(String nickname) { this.nickname = nickname;}


}
