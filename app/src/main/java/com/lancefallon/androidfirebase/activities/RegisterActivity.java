package com.lancefallon.androidfirebase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
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

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = getClass().getName();
    private EditText mEmailText;
    private EditText mPasswordText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.register_activity_registerButton).setOnClickListener(this);
        mEmailText = (EditText)findViewById(R.id.register_activity_email);
        mPasswordText = (EditText)findViewById(R.id.register_activity_password);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseAuth.getCurrentUser() != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + firebaseUser.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.register_activity_registerButton){

            //TODO validate
            String email = mEmailText.getText().toString();
            String password = mPasswordText.getText().toString();
            final FirebaseApplication firebaseApplication = this.application;
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithEmail:failed", task.getException());
                        Toast.makeText(RegisterActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();
                    } else{
                        firebaseApplication.getAuth().signinWithEmailAndPassword(FirebaseAuth.getInstance().getCurrentUser());
                        finishLogin();
                    }
                }
            });
        }
    }

    protected void finishLogin(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
        return;
    }
}
