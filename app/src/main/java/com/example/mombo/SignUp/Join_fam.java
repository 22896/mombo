package com.example.mombo.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mombo.Main.LoginActivity;
import com.example.mombo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Join_fam extends AppCompatActivity {

    EditText input_email;
    EditText input_pswd;
    Button input_signup;
    EditText input_nickname;
    EditText input_phone;
    EditText input_hnumber;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    private DatabaseReference mDatabase;

    final String FIRESTORE_TAG = "[FIRESTORE_TAG]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        input_email = (EditText) findViewById(R.id.input_email);
        input_pswd = (EditText) findViewById(R.id.input_pswd);
        input_nickname = (EditText) findViewById((R.id.input_nickname));
        input_phone = (EditText) findViewById((R.id.input_phone));
        input_hnumber = (EditText) findViewById((R.id.famNum_edit));

        input_signup = (Button) findViewById(R.id.input_signup);
        input_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = input_email.getText().toString().trim();
                final String pswd = input_pswd.getText().toString().trim();
                final String nickname = input_nickname.getText().toString().trim();
                final String phone = input_phone.getText().toString().trim();
                final String hnumber = input_hnumber.getText().toString().trim();


                if ((email != null && !email.isEmpty()) && (pswd != null && !pswd.isEmpty())
                        && (nickname != null && !nickname.isEmpty()) && (phone != null && !phone.isEmpty()) &&  (hnumber != null && !hnumber.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(email, pswd)
                            .addOnCompleteListener(Join_fam.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Map<String, Object> user = new HashMap<>();
                                        user.put("nickname", nickname);
                                        user.put("phone", phone);
                                        user.put("hnumber", hnumber);
                                        user.put("email", email);
                                        user.put("pswd", pswd);

                                        db.collection("users")
                                                .add(user)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.d(FIRESTORE_TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(FIRESTORE_TAG, "Error adding document", e);
                                                    }
                                                });

                                        Toast.makeText(Join_fam.this, "등록 성공!", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(Join_fam.this, LoginActivity.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(Join_fam.this, "비밀번호는 문자와 숫자 혼합으로 6자리 이상 지정해주세요.", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                } else {
                    Toast.makeText(Join_fam.this, "회원가입 실패!", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}

