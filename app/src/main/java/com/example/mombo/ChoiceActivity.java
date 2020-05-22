package com.example.mombo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        ImageButton Button1 = (ImageButton) findViewById(R.id.imageButton1);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Join_mom.class);
                startActivity(intent);
            }
        });

        ImageButton Button2 = (ImageButton) findViewById(R.id.imageButton2);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Join_mom.class);
                startActivity(intent);
            }
        });


    }
}
