package com.alcorsys.medianearby.models;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/25/12
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoggedInStatus {
    private boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
