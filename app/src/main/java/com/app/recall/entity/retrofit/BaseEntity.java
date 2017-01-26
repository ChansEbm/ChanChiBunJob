package com.app.recall.entity.retrofit;

import org.litepal.crud.DataSupport;

/**
 * Created by KenChan on 16/12/13.
 */

public class BaseEntity extends DataSupport {

    public int code;
    public String message;
    public String Error;


    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
