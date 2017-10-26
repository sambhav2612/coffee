package com.example.android.justjava;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Sambhav Jain on 10/18/2017.
 * @author Sambhav Jain
 */

public class NotificationHelper extends AppCompatActivity {

    int mNotificationId = 0;
    android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationHelper.this);

    public MainActivity activity;

    Intent intent = new Intent(activity, MainActivity.class);
    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    public NotificationHelper(String subject, String message) {
        mBuilder = new NotificationCompat.Builder(NotificationHelper.this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(subject)
                        .setContentText(message);

        mBuilder.setContentIntent(pIntent);

        // Sets an ID for the notification
        mNotificationId++;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
