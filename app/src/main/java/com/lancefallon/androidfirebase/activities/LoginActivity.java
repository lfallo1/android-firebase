package com.lancefallon.androidfirebase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseActivity;
import com.lancefallon.androidfirebase.infrastructure.FirebaseApplication;


public class LoginActivity extends BaseActivity implements View.OnClickListener, OnCompleteListener<AuthResult>  {

    private static final String TAG = "LoginActivity";

    private Button loginButton;
    private EditText emailText;
    private EditText passwordText;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(this.application.getAuth().getUser().getIsLoggedIn()){
            finishLogin();
        }

        emailText = (EditText)findViewById(R.id.login_activity_emailText);
        passwordText = (EditText)findViewById(R.id.login_activity_password);
        loginButton = (Button)findViewById(R.id.login_activity_loginButton);
        loginButton.setOnClickListener(this);
        findViewById(R.id.login_activity_registerButton).setOnClickListener(this);

        final FirebaseApplication firebaseApplication = this.application;
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    firebaseApplication.getAuth().signinWithEmailAndPassword(firebaseUser);
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + firebaseUser.getUid());
                    finishLogin();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
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

    public void login(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this);
    }

    //handle on clicks
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_activity_loginButton){
            login();
        } else if(v.getId() == R.id.login_activity_registerButton){
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    //handle signin event
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.
        if (!task.isSuccessful()) {
            Log.w(TAG, "signInWithEmail:failed", task.getException());
            Toast.makeText(this, "Auth failed",
                    Toast.LENGTH_SHORT).show();
        }
    }

    protected void finishLogin(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
        return;
    }

    protected void finishSignout(){
        this.application.getAuth().signout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        return;
    }
}
