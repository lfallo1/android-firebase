package com.lancefallon.androidfirebase.infrastructure;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

import java.net.URL;

/**
 * Created by lancefallon on 8/17/16.
 */
public class User {
    private String uid;
    private String displayName;
    private String email;
    private Uri photoUrl;
    private Boolean hasPassword;
    private Boolean isLoggedIn;

    public User(FirebaseUser firebaseUser, Boolean hasPassword){
        this.setEmail(firebaseUser.getEmail());
        this.setPhotoUrl(firebaseUser.getPhotoUrl());
        this.setDisplayName(firebaseUser.getDisplayName());
        this.setUid(firebaseUser.getUid());
        this.hasPassword = hasPassword;
        this.isLoggedIn = true;
    }

    public User(){
        this.isLoggedIn = false;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(Boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
