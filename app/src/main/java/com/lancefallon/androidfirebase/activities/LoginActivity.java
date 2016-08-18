package com.lancefallon.androidfirebase.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseActivity;
import com.lancefallon.androidfirebase.infrastructure.User;

import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(this.application.getAuth().getUser().isLoggedIn()){
            finishLogin();
        }

        Button loginButton = (Button)findViewById(R.id.login_activity_loginButton);
        loginButton.setOnClickListener(this);
    }

    public void login(){
        Firebase firebase = new Firebase("https://android-firebase-e43b9.firebaseio.com/Users/123");
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("MAIN_ACTIVITY", "Logging in: " + dataSnapshot.getValue().toString());
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                application.getAuth().getUser().updateFromMap(map);
                finishLogin();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("MAIN_ACTIVITY", firebaseError.getMessage());
            }
        });
    }

    public void finishLogin(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
        return;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_activity_loginButton){
            login();
        }
    }
}
