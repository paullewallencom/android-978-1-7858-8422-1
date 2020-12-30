package com.packtpub.masteringandroidapp;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import org.json.JSONObject;

/**
 * Created by antonio on 01/08/2015.
 */
public class MyNotificationReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle("Title")
                .setContentText("Text")
                .setSmallIcon(R.drawable.ic_launcher)
                .build();
        NotificationManagerCompat.from(context).notify(1231,notification);
    }

}
