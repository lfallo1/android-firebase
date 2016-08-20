package com.lancefallon.androidfirebase.infrastructure;

import android.app.Application;
import android.util.Log;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by lancefallon on 8/17/16.
 */
public class FirebaseApplication extends Application {


    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);

//        Firebase.setAndroidContext(this);

        if(!FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        //set user on app start
        //Note that there is a bogus error: E/DynamiteModule: Failed to load module descriptor class: Didn't find class "com.google.android.gms.dynamite.descriptors.com.google.firebase.auth.ModuleDescriptor"
        //Firebase has said this is logged as an error by mistake and is really just a debug. Unfortunately, it causes the android app to show an error saying "App has stopped."
        //More cool firebase stuff.
        //http://stackoverflow.com/questions/37370258/failed-to-load-module-descriptor-class-didnt-find-class-com-google-android-gm
        try {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if (firebaseUser != null) {
                auth.signinWithEmailAndPassword(firebaseUser);
            }
        }
        catch(Exception e){
            Log.e("Application", e.getMessage());
        }
    }



    public Auth getAuth() {
        return auth;
    }
}
