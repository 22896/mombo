package com.example.mombo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class selfdiagnosis9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfdiagnosis9);

        ImageButton Button2 = (ImageButton) findViewById(R.id.imageButton81);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
            }
        });
    }
}