package com.obe.quranid2.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.obe.quranid2.R;

import java.util.Objects;


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class PushNotificationService extends FirebaseMessagingService {
    @SuppressLint("WrongConstant")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String title = Objects.requireNonNull(remoteMessage.getNotification()).getTitle();
        String text = remoteMessage.getNotification().getBody();
        final String  CHANNEL_ID = "HEADS_UP_NOTIFICATION";
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Heads Up Notification",
                    NotificationManager.IMPORTANCE_MAX
            );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification.Builder notification = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1, notification.build());
        }
        super.onMessageReceived(remoteMessage);
    }
}
