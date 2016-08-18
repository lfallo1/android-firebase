package com.lancefallon.androidfirebase.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseAuthenticatedActivity;
import com.lancefallon.androidfirebase.infrastructure.User;

import java.util.Map;

public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener, ValueEventListener{

    private Button mSendDataButton;
    private TextView mLoggedInUser;
    private EditText mValueField;
    private EditText mKeyField;
    private User user;
    private Firebase mRootRef;
    private Firebase mUserRef;

    private String userId = "123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFirebaseAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Log.d("MAIN_ACTIVITY", "You are logged in and viewing the main activity");

        user = application.getAuth().getUser();
        mRootRef = new Firebase("https://android-firebase-e43b9.firebaseio.com/Users");
        mUserRef = mRootRef.child(userId);
        mUserRef.addValueEventListener(this);

        mValueField = (EditText)findViewById(R.id.main_activity_valueField);
        mKeyField = (EditText)findViewById(R.id.main_activity_keyField);
        mLoggedInUser = (TextView)findViewById(R.id.main_activity_username);
        mSendDataButton = (Button)findViewById(R.id.main_activity_sendDataBtn);
        mSendDataButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.main_activity_sendDataBtn){
            if(mValueField.length() > 0 && mKeyField.length() > 0){
                String value = mValueField.getText().toString();
                String key = mKeyField.getText().toString();
                Firebase childRef = mUserRef.child(key);
                childRef.setValue(value);
                mValueField.getText().clear();
            }
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d("MAIN_ACTIVITY", "USER CHANGED: " + dataSnapshot.getValue().toString());
        Map<String, String> map = dataSnapshot.getValue(Map.class);
        user.updateFromMap(map);
        mLoggedInUser.setText(user.getUsername());
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        Log.e("MAIN_ACTIVITY", firebaseError.getMessage());
    }
}
