package com.example.mombo.Mom_SelfDiagnosis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import com.example.mombo.Main.FirstActivity;
import com.example.mombo.Main.CheckActivity;
import com.example.mombo.Main.CameraActivity;
import com.example.mombo.R;
import com.example.mombo.Myaccount.MyaccountActivity;
import com.example.mombo.Main.ChattingActivity;

public class MomSelfdiagnosisActivity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mom_selfdiagnosis7);

        ImageButton Button6 = (ImageButton) findViewById(R.id.imageButton8);
        Button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), MyaccountActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button5 = (ImageButton) findViewById(R.id.message);
        Button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });


        ImageButton Button4 = (ImageButton) findViewById(R.id.list);
        Button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                startActivity(intent);
            }
        });

        Button Button30 = (Button) findViewById(R.id.next);
        Button30.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), MomSelfdiagnosisActivity8.class);
                startActivity(intent);
            }
        });

    }
}
