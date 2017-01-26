package com.app.recall.entity.retrofit.params;

/**
 * Created by KenChan on 16/12/20.
 */

public class Password {
    private String password;
    private String code;

    public Password(String code, String password) {
        this.code = code;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
