package com.app.recall.entity.retrofit;

import java.util.List;

/**
 * Created by KenChan on 17/1/19.
 */

public class ContactListEntity extends BaseEntity{


    private List<ContactBean> Contact;

    public List<ContactBean> getContact() { return Contact;}


    public void setContact(List<ContactBean> Contact) { this.Contact = Contact;}

    public static class ContactBean {
        /**
         * cid : xxx
         * realName : xxx
         * profilePhoto : htttps://xxx
         * platforms : [{"uid":"","name":"","openId":"","profilePhoto":"","username":"",
         * "nickname":""}]
         */

        private String cid;
        private String realName;
        private String profilePhoto;
        private List<PlatformsBean> platforms;

        public String getCid() { return cid;}

        public void setCid(String cid) { this.cid = cid;}

        public String getRealName() { return realName;}

        public void setRealName(String realName) { this.realName = realName;}

        public String getProfilePhoto() { return profilePhoto;}

        public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto;}

        public List<PlatformsBean> getPlatforms() { return platforms;}

        public void setPlatforms(List<PlatformsBean> platforms) { this.platforms = platforms;}

        public static class PlatformsBean {
            /**
             * uid :
             * name :
             * openId :
             * profilePhoto :
             * username :
             * nickname :
             */

            private String uid;
            private String name;
            private String openId;
            private String profilePhoto;
            private String username;
            private String nickname;

            public String getUid() { return uid;}

            public void setUid(String uid) { this.uid = uid;}

            public String getName() { return name;}

            public void setName(String name) { this.name = name;}

            public String getOpenId() { return openId;}

            public void setOpenId(String openId) { this.openId = openId;}

            public String getProfilePhoto() { return profilePhoto;}

            public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto;}

            public String getUsername() { return username;}

            public void setUsername(String username) { this.username = username;}

            public String getNickname() { return nickname;}

            public void setNickname(String nickname) { this.nickname = nickname;}
        }
    }
}
