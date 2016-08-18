package com.lancefallon.androidfirebase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseAuthenticatedActivity;
import com.lancefallon.androidfirebase.infrastructure.User;

public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener {

    private Button mSendDataButton;
    private TextView mLoggedInUser;
    private EditText mValueField;
    private EditText mKeyField;
    private Button mSignoutButton;
    private User user;
    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFirebaseAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Log.d("MAIN_ACTIVITY", "You are logged in and viewing the main activity");

        user = application.getAuth().getUser();
        mRootRef = new Firebase("https://android-firebase-e43b9.firebaseio.com/");

        mValueField = (EditText)findViewById(R.id.main_activity_valueField);
        mKeyField = (EditText)findViewById(R.id.main_activity_keyField);
        mLoggedInUser = (TextView)findViewById(R.id.main_activity_username);
        mSendDataButton = (Button)findViewById(R.id.main_activity_sendDataBtn);
        mSignoutButton = (Button)findViewById(R.id.activity_main_signoutButton);
        mSignoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_main_signoutButton){
            this.application.getAuth().signout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
    }
}
