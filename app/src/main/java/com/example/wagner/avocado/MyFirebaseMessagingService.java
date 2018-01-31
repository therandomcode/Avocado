package com.example.wagner.avocado;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * Created by jfteppa on 1/31/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        RemoteMessage.Notification notification = remoteMessage.getNotification();

        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        Log.d(TAG, "Message Notification Title: " + remoteMessage.getNotification().getTitle());

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Setting up Notification channels for android O and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationManager = setupChannels(notificationManager);
        }

        int notificationId = new Random().nextInt(60000);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "admin_channel")
            .setSmallIcon(R.drawable.agromovil_logo) //a resource for your custom small icon
            .setContentTitle(remoteMessage.getNotification().getTitle()) //the "title" value you sent in your notification
            .setContentText(remoteMessage.getNotification().getBody()) //ditto
            .setAutoCancel(true) //dismisses the notification on click
            .setSound(defaultSoundUri);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationManager setupChannels(NotificationManager notificationManager){
        CharSequence adminChannelName = "Global channel";
        String adminChannelDescription = "Notifications sent from the app admin";
        NotificationChannel adminChannel = new NotificationChannel("admin_channel", adminChannelName, NotificationManager.IMPORTANCE_LOW);

        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);

        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }

        return notificationManager;
    }
}
