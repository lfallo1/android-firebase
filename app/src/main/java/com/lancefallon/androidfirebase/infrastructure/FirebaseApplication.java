package com.lancefallon.androidfirebase.infrastructure;

import android.app.Application;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        //set user on app start
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            auth.signinWithEmailAndPassword(firebaseUser);
        }
    }



    public Auth getAuth() {
        return auth;
    }
}
