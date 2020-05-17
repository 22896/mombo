package com.example.mombo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Record extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        ImageButton Button1 = (ImageButton) findViewById(R.id.imageButton9);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button2 = (ImageButton) findViewById(R.id.imageButton10);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), camera.class);
                startActivity(intent);
            }
        });


        ImageButton Button4 = (ImageButton) findViewById(R.id.imageButton12);
        Button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), Record.class);
                startActivity(intent);
            }
        });

        ImageButton Button5 = (ImageButton) findViewById(R.id.imageButton11);
        Button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), talk.class);
                startActivity(intent);
            }
        });

        ImageButton Button6 = (ImageButton) findViewById(R.id.imageButton8);
        Button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), myaccount.class);
                startActivity(intent);
            }
        });

    }
}
