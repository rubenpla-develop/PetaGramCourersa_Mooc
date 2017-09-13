package com.rubenpla.develop.petagramcoursera.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rubenpla.develop.petagramcoursera.MainActivity;
import com.rubenpla.develop.petagramcoursera.R;

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = NotificationService.class.getName();

    private String payload;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase
     *                      Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            payload = remoteMessage.getData().get("key_firebase_1");
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("key_firebase_1", payload);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setSmallIcon(R.drawable.shock_rave_bonus_icon)
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setAutoCancel(true)
                    .setSubText(String.valueOf(remoteMessage.getSentTime()));


            NotificationManager manager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);

            manager.notify(0, notificationBuilder.build());
        }
    }
}
