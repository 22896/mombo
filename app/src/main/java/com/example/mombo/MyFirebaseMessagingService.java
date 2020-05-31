package com.example.mombo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.mombo.Main.MainActivity;
import com.example.mombo.MyJobService;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.net.URLDecoder;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {


   /* @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("Firebase", "FirebaseInstanceIDService : " + s);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage != null && remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }





    private void sendNotification(RemoteMessage remoteMessage) {

        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelid= "alarm_channel_id";
            String channelname = "알람 테스트";

            NotificationManager notichannel = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channelMessage = new NotificationChannel(channelid, channelname,
                    android.app.NotificationManager.IMPORTANCE_DEFAULT);
            channelMessage.setDescription("채널에 대한 설명.");
            channelMessage.enableLights(true);
            channelMessage.enableVibration(true);
            channelMessage.setShowBadge(false);
            channelMessage.setVibrationPattern(new long[]{100, 200, 100, 200});
            notichannel.createNotificationChannel(channelMessage);

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channelid)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setChannelId(channelid)
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(9999, notificationBuilder.build());

        } else {
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, "")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(9999, notificationBuilder.build());

        }
        NotificationCompat.Builder notificationBuilder = null;
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)



        {
            String channelId = "alarm_channel_id";
            String channelDescription = "Default Channel";
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelId, channelDescription, importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            notificationBuilder = new NotificationCompat.Builder(this, channelId);
        } else {
            notificationBuilder = new NotificationCompat.Builder(this);
        }

    }
*/

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
        Intent intent = new Intent(this, MainActivity.class);
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





    // Also if you intend on generating your own notifications as a result of a received FCM
    // message, here is where that should be initiated. See sendNotification method below.


   /* @Override
    public void onMessageReceived(RemoteMessage remoteMessage){



            // Also if you intend on generating your own notifications as a result of a received FCM
            // message, here is where that should be initiated. See sendNotification method below.

       Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            title = remoteMessage.getData().get("title");
            body = remoteMessage.getData().get("body");
            color = remoteMessage.getData().get("color");

            if (true){
                scheduleJob();
            } else {
                handleNow();
            }
        }

        if (remoteMessage.getNotification() !=null){
            Log.d(TAG, "Message Notification Body:" + remoteMessage.getNotification().getColor());
            Log.d(TAG, "Message Notification Body:" + remoteMessage.getNotification().getIcon());
            Log.d(TAG, "Message Notification Body:" + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Message Notification Body:" + remoteMessage.getNotification().getBody());

        }
        sendNotification();
    }



    @Override
    public void onNewToken(String token){
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }
    private void scheduleJob(){
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJOb = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJOb);
    }
    private void handleNow(){
        Log.d(TAG, "Short lived task is done.");
    }
    private void sendRegistrationToServer(String token){
    }
    private void sendNotification(){
        Intent intent =new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId ="alarm_channel_id";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setColor(Color.parseColor(color))
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setPriority(Notification.PRIORITY_HIGH);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
                notificationManager.notify(0, notificationBuilder.build());
    }
*/


}
