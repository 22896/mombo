package com.example.mombo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.mombo.Main.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {


    private  static final String TAG = "MyFirebaseMessageService";

    private String title = "";
    private String body = "";
    private String color = "";


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage)
    {
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");



        sendNotification(title, body);



    }



    private void sendNotification(String title, String body)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String chId = "alarm_channel_id";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);// 알림 왔을때 사운드.
      Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), defaultSoundUri);
        ringtone.play();

        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this, "alarm_channel_id")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);



        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


/* 안드로이드 오레오 버전 대응 */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String chName = "alarm_channel_id";
            NotificationChannel channel = new NotificationChannel("alarm_channel_id", chName, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);

        }
        manager.notify(0, notiBuilder.build());

    }





   
}
