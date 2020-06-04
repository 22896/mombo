package com.example.mombo.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mombo.R;

public class alarm extends AppCompatActivity {
ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        back_btn = (ImageButton) findViewById(R.id.back);

        ImageButton Button1 = (ImageButton) findViewById(R.id.back);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
