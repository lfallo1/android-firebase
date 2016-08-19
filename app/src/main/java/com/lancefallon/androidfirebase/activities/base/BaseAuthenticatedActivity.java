package com.lancefallon.androidfirebase.activities.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lancefallon.androidfirebase.activities.LoginActivity;

public abstract class BaseAuthenticatedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        onFirebaseAppCreate(savedInstanceState);
    }

    protected abstract void onFirebaseAppCreate(Bundle savedInstanceState);
}
