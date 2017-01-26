package com.app.recall.entity.retrofit.params;

/**
 * Created by KenChan on 17/1/26.
 */

public class Plat {
    private String name;
    private String username;

    public Plat(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
