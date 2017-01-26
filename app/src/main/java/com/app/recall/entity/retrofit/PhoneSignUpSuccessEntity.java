package com.app.recall.entity.retrofit;

/**
 * Created by KenChan on 16/12/20.
 */

public class PhoneSignUpSuccessEntity extends BaseEntity {

    /**
     * user : {"uid":"585899dd4d2d6f09c3616168","username":"","realName":"",
     * "phone":"8613250079367","email":"","facebookId":"","profilePhoto":""}
     * token : {"accessToken":"wZL0E47Rji7tp7ykhxi8WUo4lAhFsy2PBaEPdSNRyEs=",
     * "refreshToken":"hr-TS4j4yzjSPCiAWBW-JKNcU3CULMTDFhVmv1Gxchg="}
     */

    private UserBean user;
    private TokenBean token;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public TokenBean getToken() {
        return token;
    }

    public void setToken(TokenBean token) {
        this.token = token;
    }

    public static class UserBean {
        /**
         * uid : 585899dd4d2d6f09c3616168
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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFacebookId() {
            return facebookId;
        }

        public void setFacebookId(String facebookId) {
            this.facebookId = facebookId;
        }

        public String getProfilePhoto() {
            return profilePhoto;
        }

        public void setProfilePhoto(String profilePhoto) {
            this.profilePhoto = profilePhoto;
        }
    }

    public static class TokenBean {
        /**
         * accessToken : wZL0E47Rji7tp7ykhxi8WUo4lAhFsy2PBaEPdSNRyEs=
         * refreshToken : hr-TS4j4yzjSPCiAWBW-JKNcU3CULMTDFhVmv1Gxchg=
         */

        private String accessToken;
        private String refreshToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
