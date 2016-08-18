package com.lancefallon.androidfirebase.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseAuthenticatedActivity;
import com.lancefallon.androidfirebase.infrastructure.User;

public class MainActivity extends BaseAuthenticatedActivity {

    TextView mLoggedInUser;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFirebaseAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Log.d("MAIN_ACTIVITY", "You are logged in and viewing the main activity");

        user = application.getAuth().getUser();
        mLoggedInUser = (TextView)findViewById(R.id.main_activity_username);
        mLoggedInUser.setText(user.getUsername());
    }
}
