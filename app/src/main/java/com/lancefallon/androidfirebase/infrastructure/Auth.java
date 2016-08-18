package com.lancefallon.androidfirebase.infrastructure;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by lancefallon on 8/17/16.
 */
public class Auth {
    private final Context context;
    private User user;

    public Auth(Context context) {
        this.context = context;
        user = new User();
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user){
        this.user = user;
    }

    public void signinWithEmailAndPassword(FirebaseUser firebaseAuth) {
        setUser(new User(firebaseAuth, true));
    }

    public void signout() {
        user = new User();
    }
}
