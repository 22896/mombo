package com.example.mombo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Fam_Join extends AppCompatActivity {

    EditText input_email;
    EditText input_pswd;
    EditText input_nickname;
    EditText input_phone;
    EditText input_hnumber;
    ProgressBar mLoadingIndicatorProgress;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    final String FIRESTORE_TAG = "[FIRESTORE_TAG]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fam_join);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        input_email = (EditText) findViewById(R.id.input_email);
        input_pswd = (EditText) findViewById(R.id.input_pswd);
        input_nickname = (EditText) findViewById((R.id.nickname));
        input_phone = (EditText) findViewById((R.id.phone));
        input_hnumber = (EditText) findViewById((R.id.hnumber));
        mLoadingIndicatorProgress = findViewById(R.id.loading_indicator_pb);
        mLoadingIndicatorProgress.setVisibility(View.INVISIBLE);

        findViewById(R.id.sign_up).setOnClickListener(signupClicked);

    }

    private View.OnClickListener signupClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String email = input_email.getText().toString();
            String pswd = input_pswd.getText().toString();
            String nickname = input_nickname.getText().toString();
            String phone = input_phone.getText().toString();
            String hnumber = input_hnumber.getText().toString();

            if ((TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                Toast.makeText(Fam_Join.this, "사용할 수 없는 이메일입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if ((pswd.isEmpty() && pswd.length() < 6)) {
                Toast.makeText(Fam_Join.this, "6자 이상으로 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            if (nickname.isEmpty() && phone.isEmpty() && hnumber.isEmpty()) {
                Toast.makeText(Fam_Join.this, "빈칸을 다 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            signup(email, pswd, nickname, phone, hnumber);
        }
    };

    private void signup(final String email, final String pswd, final String nickname, final String phone, final String hnumber) {

        mLoadingIndicatorProgress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pswd)
                .addOnCompleteListener(Fam_Join.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mLoadingIndicatorProgress.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {

                            Map<String, Object> user = new HashMap<>();
                            user.put("nickname", nickname);
                            user.put("phone", phone);
                            user.put("hnumber", hnumber);
                            user.put("email", email);
                            user.put("pswd", pswd);
                            user.put("div", 2);

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

                            Toast.makeText(Fam_Join.this, "등록 성공!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Fam_Join.this, LoginActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(Fam_Join.this, "비밀번호는 문자와 숫자 혼합으로 6자리 이상 지정해주세요.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

//                } else {
//                    Toast.makeText(Fam_Join.this, "회원가입 실패!", Toast.LENGTH_SHORT).show();
//                }


    }
}