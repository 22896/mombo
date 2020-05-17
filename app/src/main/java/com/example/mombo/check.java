package com.example.mombo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        ImageButton Button9 = (ImageButton) findViewById(R.id.imageButton9);
        Button9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button10 = (ImageButton) findViewById(R.id.imageButton10);
        Button10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), camera.class);
                startActivity(intent);
            }
        });

        ImageButton Button11 = (ImageButton) findViewById(R.id.imageButton11);
        Button11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), talk.class);
                startActivity(intent);
            }
        });

        ImageButton Button12 = (ImageButton) findViewById(R.id.imageButton12);
        Button12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), Record.class);
                startActivity(intent);
            }
        });


    }


}
