package com.app.recall.entity.platform;

/**
 * Created by KenChan on 17/1/11.
 */

public class PlatformEntity {
    private boolean isAuth;
    private String name;

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
