package com.example.mombo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageButton Button2 = (ImageButton) findViewById(R.id.home);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), Record.class);
                startActivity(intent);
            }
        });

        ImageButton Button21 = (ImageButton) findViewById(R.id.imageButton8);
        Button21.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), myaccount.class);
                startActivity(intent);
            }
        });

        ImageButton Button18 = (ImageButton) findViewById(R.id.message);
        Button18.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), talk.class);
                startActivity(intent);
            }
        });
    }









}
