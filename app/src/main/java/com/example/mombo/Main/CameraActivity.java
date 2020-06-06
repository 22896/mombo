package com.example.mombo.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mombo.R;
import com.example.mombo.Myaccount.MyaccountActivity;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageButton Button2 = (ImageButton) findViewById(R.id.home);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button17 = (ImageButton) findViewById(R.id.cam);
        Button17.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button16 = (ImageButton) findViewById(R.id.list);
        Button16.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button21 = (ImageButton) findViewById(R.id.imageButton8);
        Button21.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), MyaccountActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button18 = (ImageButton) findViewById(R.id.message);
        Button18.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });
    }









}
