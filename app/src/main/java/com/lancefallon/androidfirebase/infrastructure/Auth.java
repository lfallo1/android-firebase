package com.lancefallon.androidfirebase.infrastructure;

import android.content.Context;

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

    public void setUser(User user){
        this.user = user;
    }
}
