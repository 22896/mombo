package com.example.mombo.Myaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mombo.Main.FirstActivity;
import com.example.mombo.Main.MainActivity;
import com.example.mombo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class myaccount extends AppCompatActivity {

    Button btnDelete;
    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        btnDelete = findViewById(R.id.btnDelete);

        database = FirebaseDatabase.getInstance().getReference().child("User Data");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            database.removeValue();
            }
        });






        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);



        Button Button1 = (Button) findViewById(R.id.updatebtn);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileResetActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button2 = (ImageButton) findViewById(R.id.back);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
            }
        });

        Button Button3 = (Button) findViewById(R.id.bt_update);
        Button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), PasswordResetActivity.class);
                startActivity(intent);
            }
        });



    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startFirstActivity();
                    break;
            }
        }
    };

    private void startFirstActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
