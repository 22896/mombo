package com.example.mombo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;


public class Mom_Main extends AppCompatActivity implements SensorEventListener {

    private static final String channel_description = "테스트임";
    private static final String CHANNEL_ID = "alarm_channel_id";
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    TextView tv_steps;
    TextView tv_nickname;
    ImageButton call;
    SensorManager sensorManager;

    boolean running = false;
    private final  String channel_name ="알람 테스트" ;
    private PendingIntent pendingIntent;


    public Mom_Main() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mom_main);

        try {
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.d("IDService", "device token : " + token);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        tv_steps = (TextView) findViewById(R.id.tv_steps);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        call = (ImageButton) findViewById((R.id.call));

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        ImageButton Button2 = (ImageButton) findViewById(R.id.myaccount);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), myaccount.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "센서가 이상해요", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        //sensorManager.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            tv_steps.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


  /*  private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("alarm_channel_id", "알람 테스트", NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("알람테스트");
            notificationManager.createNotificationChannel(notificationChannel);


        }*/

   // }
/*    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, Mom_Main.getId());
            startActivity(intent);
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID) //CHANNEL_ID 채널에 지정한 아이디
                    .setContentTitle("background machine")
                    .setContentText("알림입니다")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true).build();




        }
    }*/
   private void createNotificationChannel() {
       NotificationManager mNotificationManager =
               (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

       // The id of the channel.
       String id = "alarm_channel_id";

       // The user-visible name of the channel.
       CharSequence name = getString(R.string.channel_name);

       // The user-visible description of the channel.
       String description = getString(R.string.channel_description);

       int importance = NotificationManager.IMPORTANCE_LOW;

       NotificationChannel mChannel = new NotificationChannel(id, name, importance);

// Configure the notification channel.
       mChannel.setDescription(description);
       mChannel.enableLights(true);

// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
       mChannel.setLightColor(Color.RED);
       mChannel.enableVibration(true);
       mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

       mNotificationManager.createNotificationChannel(mChannel);


   }

}