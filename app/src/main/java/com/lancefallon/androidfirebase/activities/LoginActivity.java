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
import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseActivity;
import com.lancefallon.androidfirebase.infrastructure.FirebaseApplication;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

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
        findViewById(R.id.login_activity_loginButton).setOnClickListener(this);
        findViewById(R.id.login_activity_registerButton).setOnClickListener(this);

        //setup listener for auth state changes
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //**** There is a known bug with firebase, and this method gets triggered repeatedly instead of once on a state change.
                //As of early August Firebase has not released a fix.  So there cannot be ANY logic in this method related to state / activity lifecycle.
                //Instead I'm just stuffing any activity logic in the onComplete callback (further down this page).  Quite frankly, this listener could be wiped. it does nothing
                //http://stackoverflow.com/questions/37674823/firebase-android-onauthstatechanged-fire-twice-after-signinwithemailandpasswor
                if (firebaseAuth.getCurrentUser() != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:");
                } else {
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
        if(emailText.getText().length() > 0 && passwordText.getText().length() > 0){
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            final FirebaseApplication firebaseApplication = this.application;
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithEmail:failed", task.getException());
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        firebaseApplication.getAuth().signinWithEmailAndPassword(FirebaseAuth.getInstance().getCurrentUser());
                        finishLogin();
                    }
                }
            });
            return;
        }
        Toast.makeText(this, "Please enter both your email and password", Toast.LENGTH_SHORT).show();
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

    protected void finishLogin(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
        return;
    }
}
