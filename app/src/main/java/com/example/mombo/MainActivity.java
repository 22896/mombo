package com.example.mombo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private EditText emailTV, passwordTV;
    private Button loginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    ImageButton login_btn;
    EditText EditTextEmail;
    EditText EditTextpswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        EditTextEmail = (EditText) findViewById(R.id.input_email);
        EditTextpswd = (EditText) findViewById(R.id.input_pswd);

        login_btn = (ImageButton) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EditTextEmail.getText().toString().trim();
                String pswd = EditTextpswd.getText().toString().trim();
                final String TAG = "LOGIN_ACTIVITY;";

                if ((email != null && !email.isEmpty()) && (pswd != null && !pswd.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(email, pswd)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        Toast.makeText(MainActivity.this, "반갑습니다.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "아이디 또는 비밀번호를 다시 확인해 주세요.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }

                            });
                }

            }
        });

        Button Button2 = (Button) findViewById(R.id.btn_join);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}