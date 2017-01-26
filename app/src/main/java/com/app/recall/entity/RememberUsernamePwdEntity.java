package com.app.recall.entity;

/**
 * Created by KenChan on 17/1/25.
 */

public class RememberUsernamePwdEntity {
    private boolean isPlatform = false;
    private String userName;
    private String password;

    public RememberUsernamePwdEntity(boolean isPlatform, String password, String userName) {
        this.isPlatform = isPlatform;
        this.password = password;
        this.userName = userName;
    }

    public boolean isPlatform() {
        return isPlatform;
    }

    public void setPlatform(boolean platform) {
        isPlatform = platform;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
