package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sambhav Jain on 10/17/2017.
 * @author Sambhav Jain
 * @since 7/10/17
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(SplashActivity.this, credentials.class);
        startActivity(intent);
        finish();
    }
}
