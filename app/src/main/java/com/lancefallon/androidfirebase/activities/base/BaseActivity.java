package com.lancefallon.androidfirebase.activities.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.lancefallon.androidfirebase.infrastructure.FirebaseApplication;

public abstract class BaseActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    protected FirebaseApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (FirebaseApplication) getApplication();
    }
}
