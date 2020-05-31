package com.example.mombo.SelfDiagnosis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import com.example.mombo.Main.FirstActivity;
import com.example.mombo.Main.Record;
import com.example.mombo.Main.camera;
import com.example.mombo.R;
import com.example.mombo.Myaccount.myaccount;
import com.example.mombo.Main.talk;

public class selfdiagnosis8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfdiagnosis8);

        ImageButton Button6 = (ImageButton) findViewById(R.id.imageButton8);
        Button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), myaccount.class);
                startActivity(intent);
            }
        });

        ImageButton Button1 = (ImageButton) findViewById(R.id.home);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button2 = (ImageButton) findViewById(R.id.cam);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), camera.class);
                startActivity(intent);
            }
        });

        ImageButton Button5 = (ImageButton) findViewById(R.id.message);
        Button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), talk.class);
                startActivity(intent);
            }
        });


        ImageButton Button4 = (ImageButton) findViewById(R.id.list);
                Button4.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), Record.class);
                startActivity(intent);
            }
        });

        Button Button30 = (Button) findViewById(R.id.next);
        Button30.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), selfdiagnosis9.class);
                startActivity(intent);
            }
        });

    }
}
