package com.lancefallon.androidfirebase.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseActivity;
import com.lancefallon.androidfirebase.infrastructure.User;

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
        User user = new User(1,"lfallo1", "lfallo1", "n/a", true, true, "fallon.lance@gmail.com");
        this.application.getAuth().setUser(user);
        finishLogin();
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
