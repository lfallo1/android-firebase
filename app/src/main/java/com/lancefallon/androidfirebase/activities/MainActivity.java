package com.lancefallon.androidfirebase.activities;

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

public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener{

    private Button mSendDataButton;
    private TextView mLoggedInUser;
    private EditText mValueField;
    private EditText mKeyField;
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
        mRootRef = new Firebase("https://android-firebase-e43b9.firebaseio.com/Users/426524252454235");

        mLoggedInUser = (TextView)findViewById(R.id.main_activity_username);
        mLoggedInUser.setText(user.getUsername());
        mSendDataButton = (Button)findViewById(R.id.main_activity_sendDataBtn);
        mSendDataButton.setOnClickListener(this);
        mValueField = (EditText)findViewById(R.id.main_activity_valueField);
        mKeyField = (EditText)findViewById(R.id.main_activity_keyField);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.main_activity_sendDataBtn){
            String value = mValueField.getText().toString();
            String key = mKeyField.getText().toString();
            if(null != value && null != key){
                Firebase childRef = mRootRef.child(key);
                childRef.setValue(value);
                mValueField.setText("");
            }
        }
    }
}
