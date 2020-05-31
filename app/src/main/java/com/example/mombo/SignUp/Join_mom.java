package com.example.mombo.SignUp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mombo.Main.FirstActivity;
import com.example.mombo.R;
import com.example.mombo.UserData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Join_mom extends AppCompatActivity {



    private EditText input_email,input_pswd,input_nickname,input_phone,input_famphone,input_hnumber,input_steps;
    private Button input_signup;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        input_email= findViewById(R.id.input_email);
        input_pswd= findViewById(R.id.input_pswd);
        input_nickname= findViewById(R.id.input_nickname);
        input_phone= findViewById(R.id.input_phone);
        input_famphone= findViewById(R.id.famNum_edit);
        input_hnumber= findViewById(R.id.hosNum_edit);
        input_steps =findViewById((R.id.steps));

        input_signup = findViewById(R.id.input_signup);

        database = FirebaseDatabase.getInstance().getReference().child("User Data");

        input_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId,userEmail,userPassword,userNickname,userMyNumber,userFamNumber,userHosNumber,userSteps;

                userId = database.push().getKey();
                userEmail = input_email.getText().toString();
                userPassword = input_pswd.getText().toString();
                userNickname = input_nickname.getText().toString();
                userMyNumber = input_phone.getText().toString();
                userFamNumber = input_famphone.getText().toString();
                userHosNumber = input_hnumber.getText().toString();
                userSteps = input_steps.getText().toString();

                if (userEmail.equals("")) {
                    Toast.makeText(Join_mom.this, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userPassword.equals("")) {
                    Toast.makeText(Join_mom.this, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userNickname.equals("")) {
                    Toast.makeText(Join_mom.this, "닉네임을 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userMyNumber.equals("")) {
                    Toast.makeText(Join_mom.this, "자신의 전화번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userFamNumber.equals("")) {
                    Toast.makeText(Join_mom.this, "가족의 전화번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userHosNumber.equals("")) {
                    Toast.makeText(Join_mom.this, "병원의 전화번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userSteps.equals("")) {
                    Toast.makeText(Join_mom.this, "걸음수를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userPassword.length() < 6) {
                    Toast.makeText(Join_mom.this, "비밀번호를 6자 이상 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userMyNumber.length() < 11) {
                    Toast.makeText(Join_mom.this, "자신의 전화번호를 11자 이상 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userFamNumber.length() < 11) {
                    Toast.makeText(Join_mom.this, "가족의 전화번호를 11자 이상 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if (userHosNumber.length() < 11) {
                    Toast.makeText(Join_mom.this, "병원의 전화번호를 11자 이상 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    UserData userData = new UserData(userId,userEmail,userPassword,userNickname,userMyNumber,userFamNumber,userHosNumber,userSteps);
                    database.child(userId).setValue(userData);
                    Toast.makeText(Join_mom.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Join_mom.this, FirstActivity.class);
                        startActivity(i);
                        finish();
                }
            }
        });
    }
}




