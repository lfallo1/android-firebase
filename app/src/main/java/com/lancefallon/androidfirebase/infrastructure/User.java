package com.lancefallon.androidfirebase.infrastructure;

import java.util.Map;

/**
 * Created by lancefallon on 8/17/16.
 */
public class User {
    private int id;
    private String username;
    private String displayName;
    private String avatarUrl;
    private Boolean isLoggedIn;
    private Boolean hasPassword;
    private String email;

    public User(){
        this.isLoggedIn = false;
        this.hasPassword = false;
    }

    public User(int id, String username, String displayName, String avatarUrl, Boolean isLoggedIn, Boolean hasPassword, String email) {
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

    public Boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public Boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(Boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updateFromMap(Map<String, String> map) {
        this.setUsername(map.get("username"));
        this.setDisplayName(map.get("displayName"));
        this.setAvatarUrl(map.get("avatarUrl"));
        this.setEmail(map.get("email"));
        this.setIsLoggedIn(true);
    }
}
