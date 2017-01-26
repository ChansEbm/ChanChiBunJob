package com.app.recall.entity.retrofit;

import android.support.annotation.NonNull;

/**
 * Created by KenChan on 16/12/14.
 */

public class UserEntity extends BaseEntity {

    /**
     * user : {"uid":"5853b44e4d2d6f09c3616151","username":"","realName":"",
     * "phone":"8613250079367","email":"","facebookId":"","profilePhoto":""}
     * token : {"accessToken":"TRn80LEqTeB0g89g-SsXrcSr50jZdeH_WI3RETYwVgg=",
     * "refreshToken":"NrU9sWfh6yPO_qtgZcti8R9smkslVk8aXl15_BWRN14="}
     */
    private String uid;
    private String username;
    private String profilephoto;

    public String getUid() { return uid;}

    public void setUid(String uid) { this.uid = uid;}

    public String getUsername() { return username;}

    public void setUsername(String username) { this.username = username;}

    public String getProfilephoto() { return profilephoto;}

    public void setProfilephoto(String profilephoto) { this.profilephoto = profilephoto;}

    private UserBean user;
    private TokenBean token;

    public UserBean getUser() { return user;}

    public void setUser(UserBean user) { this.user = user;}

    public TokenBean getToken() { return token;}

    public void setToken(TokenBean token) { this.token = token;}

    public static class UserBean {
        /**
         * uid : 5853b44e4d2d6f09c3616151
         * username :
         * realName :
         * phone : 8613250079367
         * email :
         * facebookId :
         * profilePhoto :
         */

        private String uid;
        private String username;
        private String realName;
        private String phone;
        private String email;
        private String facebookId;
        private String profilePhoto;

        public String getUid() { return uid;}

        public void setUid(String uid) { this.uid = uid;}

        public String getUsername() { return username;}

        public void setUsername(String username) { this.username = username;}

        public String getRealName() { return realName;}

        public void setRealName(String realName) { this.realName = realName;}

        public String getPhone() { return phone;}

        public void setPhone(String phone) { this.phone = phone;}

        public String getEmail() { return email;}

        public void setEmail(String email) { this.email = email;}

        public String getFacebookId() { return facebookId;}

        public void setFacebookId(String facebookId) { this.facebookId = facebookId;}

        public String getProfilePhoto() { return profilePhoto;}

        public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto;}
    }

    public static class TokenBean {
        /**
         * accessToken : TRn80LEqTeB0g89g-SsXrcSr50jZdeH_WI3RETYwVgg=
         * refreshToken : NrU9sWfh6yPO_qtgZcti8R9smkslVk8aXl15_BWRN14=
         */

        private String accessToken;
        private String refreshToken;

        public String getAccessToken() { return accessToken;}

        public void setAccessToken(String accessToken) { this.accessToken = accessToken;}

        public String getRefreshToken() { return refreshToken;}

        public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken;}
    }

    public static class Params {
        private String username;
        private String email;
        private String phone;
        private String password;
        private String facebookId;
        private String token;

        public Params(String email, @NonNull String facebookId, @NonNull String password,
                      @NonNull String phone, @NonNull String token, @NonNull String username) {
            this.email = email;
            this.facebookId = facebookId;
            this.password = password;
            this.phone = phone;
            this.token = token;
            this.username = username;
        }
    }
}
