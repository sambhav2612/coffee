package com.example.android.justjava;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int num = 0;
    private int price = 5;
    private String[] to = new String[]{""};
    private RadioButton b1 = null;
    private RadioButton b2 = null;
    private EditText ed = null;
    private EditText ed2 = null;
    private TextView text = null;
    private String name = "";
    private String email = "";
    private String msg = "";
    private String tot = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Aplication created", "");

        b1 = (RadioButton) findViewById(R.id.yes);
        b1.setChecked(false);
        b2 = (RadioButton) findViewById(R.id.no);
        b2.setChecked(false);
        ed = (EditText) findViewById(R.id.name);
        ed2 = (EditText)findViewById(R.id.email);

        if (getResources().getDisplayMetrics().widthPixels > getResources().getDisplayMetrics().
                heightPixels) {
            Toast.makeText(this, R.string.land, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.port, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Aplication started", "");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Aplication paused", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Aplication resumed", "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Aplication stopped", "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Aplication destroyed", "");
    }

    public void onClicky(View view) {

        name = ed.getText().toString().trim();
        email = ed2.getText().toString().trim();
        text = (TextView) findViewById(R.id.main);

        if (b2.isChecked()) {
            b2.setChecked(true);
            price += 2; // 2 dollars per cup
        }

        if (!name.equals("")) {
            msg = getString(R.string.hi) + " " + name + getString(R.string.new_line) + getString(R.string.radio_app);
        } else {
            msg = getString(R.string.radio_app);
        }

        text.setText(msg);
    }

    public void tellAboutCupPrice(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msg);
        builder.show();
    }

    public void submitOrder(final View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(R.string.dialog_title);
        alert.setMessage(R.string.dialog_msg);

        String n = "", sEmail = "";

        if (name.equals(""))
            n += getString(R.string.name) + getString(R.string.colon) + getString(R.string.space) + getString(R.string.no_name);
        else
            n += getString(R.string.name) + getString(R.string.colon) + getString(R.string.space) + name;

        if (email.equals(""))
            sEmail += getString(R.string.email) + getString(R.string.colon) + getString(R.string.space) + getString(R.string.no_email);
        else
            sEmail += getString(R.string.email) + getString(R.string.colon) + getString(R.string.space) + email;


        String netQ = getString(R.string.quantity) + getString(R.string.colon) + getString(R.string.space) + num;
        String netP = getString(R.string.price) + getString(R.string.colon) + getString(R.string.space) + num*price;


        TextView input = new TextView(this);
        tot = n + getString(R.string.new_line) + sEmail + getString(R.string.new_line) + netQ + getString(R.string.new_line) + netP;
        input.setText(tot);
        input.setTextColor(Color.BLACK);
        input.setTextSize(16);
        input.setGravity(Gravity.CENTER_HORIZONTAL);

        alert.setCancelable(false);

        alert.setView(input);

        if (num == 0) // no change in initial values of variable
            Toast.makeText(MainActivity.this, R.string.submitERR, Toast.LENGTH_SHORT).show();

        else {
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Do something with value!
                    reset(view);
                    Toast.makeText(MainActivity.this, R.string.subC, Toast.LENGTH_SHORT).show();
                    sendEmail();
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            AlertDialog newDiag = alert.create();
            newDiag.show();

        }
    }

    public void sendEmail() {

        Random rand = new Random();
        int i = rand.nextInt(10000);
        String subject = getString(R.string.receipt) + getString(R.string.space) + getString(R.string.hash) + i;

        Log.i("Send email", "");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:" + email));

        if (!email.equals("")) to = new String[]{email};
        else to = new String[]{"sambhav.portfolio.app@gmail.com"};

        emailIntent.setType("text/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, tot);

        try {
            startActivity(emailIntent);
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkForChecked() {
        return (b1.isChecked() | b2.isChecked());
    }

    public void increase(View view) {
        if (checkForChecked()) {
            b1.setClickable(false);
            b2.setClickable(false);

            ++num;
            createString();
            displayPrice(num * price);

        } else {
            Toast.makeText(MainActivity.this, R.string.checkRB, Toast.LENGTH_SHORT).show();
        }
    }

    public void decrease(View view) {
        if (checkForChecked()) {
            b1.setClickable(false);
            b2.setClickable(false);

            if (num == 0) {//
                Toast.makeText(MainActivity.this, R.string.valBelowZERO, Toast.LENGTH_SHORT).show();
            } else {
                --num;
                createString();
                displayPrice(num * price);
            }
        } else {
            Toast.makeText(MainActivity.this, R.string.checkRB, Toast.LENGTH_SHORT).show();
        }
    }

    public void createString() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        String str = "" + num;
        quantityTextView.setText(str);
    }

    public void reset(View view) {

        if (b1.isChecked())
            b1.toggle();
        if (b2.isChecked())
            b2.toggle();

        b1.setClickable(true);
        b2.setClickable(true);

        if (num == 0 & price == 5 & name.equals("")) {
            Toast.makeText(MainActivity.this, R.string.valZERO, Toast.LENGTH_SHORT).show();

        } else {
            num = 0;
            price = 5;
            name = "";
            msg = getString(R.string.radio_app);
            text.setText(msg);
            ed.setText(name);
            ed2.setText(name);
            createString();
            displayPrice(num * price);
        }
    }

    public void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}