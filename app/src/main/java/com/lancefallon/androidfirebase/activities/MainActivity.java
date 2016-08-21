package com.lancefallon.androidfirebase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lancefallon.androidfirebase.R;
import com.lancefallon.androidfirebase.activities.base.BaseAuthenticatedActivity;

public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener {

    private TextView mLoggedInUser;
    private FirebaseAuth mAuth;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFirebaseAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Log.d("MAIN_ACTIVITY", "You are logged in and viewing the main activity");

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.main_activity_signoutButton).setOnClickListener(this);
        findViewById(R.id.main_activity_imageActivity).setOnClickListener(this);

        String name = application.getAuth().getUser().getDisplayName() != null ? application.getAuth().getUser().getDisplayName() : application.getAuth().getUser().getEmail();
        mLoggedInUser = (TextView)findViewById(R.id.main_activity_username);
        mLoggedInUser.setText(name);

        mListView = (ListView)findViewById(R.id.main_activity_listview);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://android-firebase-e43b9.firebaseio.com/Customers");

        FirebaseListAdapter<String> listAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                databaseReference) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView)v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };

        mListView.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.main_activity_signoutButton){
            mAuth.signOut();
            application.getAuth().signout();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        } else if(v.getId() == R.id.main_activity_imageActivity){
            startActivity(new Intent(MainActivity.this, ImageActivity.class));
        }
    }
}
