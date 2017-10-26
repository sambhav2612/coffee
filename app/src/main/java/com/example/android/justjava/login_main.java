package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class login_main extends AppCompatActivity {

    private EditText ed = null;
    private EditText ed2 = null;

    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        /*ed = (EditText) findViewById(R.id.name);
        ed2 = (EditText)findViewById(R.id.email);

        name = ed.getText().toString();
        email = ed2.getText().toString();*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Application started", "");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Application paused", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Application resumed", "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Application stopped", "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Application destroyed", "");
    }

    /*public void gotomain(View view) {
        /*if (name.equals("") & email.equals("")){
            if (name.equals("")) {
                ed.setError("name can not be left empty!");
                ed.requestFocus();
            }
            if (email.equals("")) {
                ed2.setError("email can not be left empty!");
                ed2.requestFocus();
            }
        }
        else {*/

            /*Intent intent = new Intent(login_main.this, MainActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            startActivity(intent);
        //}
    }*/
}
