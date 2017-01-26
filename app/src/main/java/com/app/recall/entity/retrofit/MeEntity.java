package com.app.recall.entity.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.recall.entity.item.BaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KenChan on 17/1/4.
 */

public class MeEntity extends BaseEntity {

    /**
     * uid : 5853b44e4d2d6f09c3616151
     * username :
     * realName :
     * phone : 8613250079367
     * email :
     * facebookId :
     * profilePhoto :
     * contacts : [{"cid":"cid","realName":"real","profilePhoto":"pto"}]
     * platforms : [{"uid":"","name":"","openId":"","profilePhoto":"","username":"","nickname":""}]
     */

    private String uid;
    private String username;
    private String realName;
    private String phone;
    private String email;
    private String facebookId;
    private String profilePhoto;
    private List<ContactsBean> contacts = new ArrayList<>();

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

    public List<ContactsBean> getContacts() { return contacts;}

    public void setContacts(List<ContactsBean> contacts) { this.contacts = contacts;}


    public static class ContactsBean extends BaseItem implements Parcelable {
        /**
         * cid : cid
         * realName : real
         * profilePhoto : pto
         */

        private String cid;
        private String realName;
        private String profilePhoto;
        private List<PlatformsBean> platforms = new ArrayList<>();

        public String getCid() { return cid;}

        public void setCid(String cid) { this.cid = cid;}

        public String getRealName() { return realName;}

        public void setRealName(String realName) { this.realName = realName;}

        public String getProfilePhoto() { return profilePhoto;}

        public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto;}

        public List<PlatformsBean> getPlatforms() { return platforms;}

        public void setPlatforms(List<PlatformsBean> platforms) { this.platforms = platforms;}

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cid);
            dest.writeString(this.realName);
            dest.writeString(this.profilePhoto);
        }

        public ContactsBean() {}

        protected ContactsBean(Parcel in) {
            this.cid = in.readString();
            this.realName = in.readString();
            this.profilePhoto = in.readString();
        }


        public static final Parcelable.Creator<ContactsBean> CREATOR = new Parcelable
                .Creator<ContactsBean>() {
            @Override
            public ContactsBean createFromParcel(Parcel source) {return new ContactsBean(source);}

            @Override
            public ContactsBean[] newArray(int size) {return new ContactsBean[size];}
        };

        public static class PlatformsBean extends BaseItem {
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
