package com.lancefallon.androidfirebase.activities.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lancefallon.androidfirebase.activities.LoginActivity;

public abstract class BaseAuthenticatedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!application.getAuth().getUser().getIsLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        onFirebaseAppCreate(savedInstanceState);
    }

    protected abstract void onFirebaseAppCreate(Bundle savedInstanceState);
}
