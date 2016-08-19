package com.lancefallon.androidfirebase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseAuthenticatedActivity;
import com.lancefallon.androidfirebase.infrastructure.FirebaseApplication;
import com.lancefallon.androidfirebase.infrastructure.User;

public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener {

    private TextView mLoggedInUser;
    private Button mSignoutButton;
    private User user;
    private Firebase mRootRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

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

        mLoggedInUser = (TextView)findViewById(R.id.main_activity_username);
        mSignoutButton = (Button)findViewById(R.id.activity_main_signoutButton);
        mSignoutButton.setOnClickListener(this);

        String name = application.getAuth().getUser().getDisplayName() != null ? application.getAuth().getUser().getDisplayName() : application.getAuth().getUser().getEmail();
        mLoggedInUser.setText(name);
        final FirebaseApplication firebaseApplication = this.application;
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //can't do any logic in here related to activities / life-cycle, because firebase is really cool & invokes this authStateChange method many times
                if (firebaseAuth.getCurrentUser() == null) {
                    Log.d("main activity", "onAuthStateChanged:signed_out");
                }
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_main_signoutButton){
            mAuth.signOut();
            application.getAuth().signout();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }
    }
}
