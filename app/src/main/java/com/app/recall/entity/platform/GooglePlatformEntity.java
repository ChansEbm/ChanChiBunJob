package com.app.recall.entity.platform;

/**
 * Created by KenChan on 17/1/13.
 */

public class GooglePlatformEntity {
    private String email;
    private String id;

    public GooglePlatformEntity(String email, String id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }
}
