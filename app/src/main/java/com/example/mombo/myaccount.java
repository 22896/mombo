package com.example.mombo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class myaccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);


        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);



        Button Button1 = (Button) findViewById(R.id.modify);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mom_join.class);
                startActivity(intent);
            }
        });

        ImageButton Button2 = (ImageButton) findViewById(R.id.back);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        Button Button3 = (Button) findViewById(R.id.sendButton);
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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
