package com.alcorsys.medianearby.models;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/25/12
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginStatus{
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
