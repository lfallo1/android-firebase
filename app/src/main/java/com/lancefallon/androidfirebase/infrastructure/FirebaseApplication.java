package com.lancefallon.androidfirebase.infrastructure;

import android.app.Application;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by lancefallon on 8/17/16.
 */
public class FirebaseApplication extends Application {


    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
        Firebase.setAndroidContext(this);
    }



    public Auth getAuth() {
        return auth;
    }
}
