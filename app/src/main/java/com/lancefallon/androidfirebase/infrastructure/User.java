package com.lancefallon.androidfirebase.infrastructure;

/**
 * Created by lancefallon on 8/17/16.
 */
public class User {
    private int id;
    private String username;
    private String displayName;
    private String avatarUrl;
    private boolean isLoggedIn;
    private boolean hasPassword;
    private String email;

    public User(){}

    public User(int id, String username, String displayName, String avatarUrl, boolean isLoggedIn, boolean hasPassword, String email) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.isLoggedIn = isLoggedIn;
        this.hasPassword = hasPassword;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
