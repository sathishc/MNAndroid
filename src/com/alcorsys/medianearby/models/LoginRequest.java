package com.alcorsys.medianearby.models;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/25/12
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginRequest{
    private String user_email;
    private String user_password;

    public LoginRequest(String user_email, String user_password){
        this.setUser_email(user_email);
        this.setUser_password(user_password);
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
