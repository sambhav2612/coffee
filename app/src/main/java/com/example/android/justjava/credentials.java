package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class credentials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credentials);
    }

    public void gotologin(View view) {
        startActivity(new Intent(credentials.this, login_main.class));
    }

    public void gotosignup(View view) {
        startActivity(new Intent(credentials.this, signup_main.class));
    }

    public void aboutApp(View view) {
        startActivity(new Intent(credentials.this, about.class));
    }
}
