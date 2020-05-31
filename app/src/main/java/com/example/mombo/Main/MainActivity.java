package com.example.mombo.Main;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mombo.R;
import com.example.mombo.SingUp.Join_mom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity {

    private ImageButton login_btn;
    private EditText input_email, input_pswd;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.d("IDService", "device token : " + token);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        mAuth = FirebaseAuth.getInstance();

        input_email = findViewById(R.id.input_email);
        input_pswd = findViewById(R.id.input_pswd);

        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();
            }
        });

        Button Button1 = (Button) findViewById(R.id.btn_join);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), Join_mom.class);
                startActivity(intent);
            }
        });

    }

    private void signIn() {
        String email, password;
        email = input_email.getText().toString().trim();
        password = input_pswd.getText().toString().trim();

        if (email.equals("")) {
            Toast.makeText(MainActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show();
        }
        else if (password.equals("")) {
            Toast.makeText(MainActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
        }
        else if (password.length() < 6) {
            Toast.makeText(MainActivity.this, "비밀번호는 6자 이상 입력해주세요.", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(i);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "문제가 발생하였습니다.", Toast.LENGTH_LONG).show();

                    }


                }
            });
        }


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
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