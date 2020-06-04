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

import com.example.mombo.PreferenceUtil;
import com.example.mombo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    private DatabaseReference userRef;

    private EditText signUp_email_edit;
    private EditText signUp_password_edit;
    private Button signUp_btn;
    String tempKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        userRef = mDatabase.getReference("UserData");

        initView();
    }

    public void signup(View view) {
        final String email = signUp_email_edit.getText().toString();
        final String passsord = signUp_password_edit.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, passsord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "확인", Toast.LENGTH_SHORT).show();
                            final FirebaseUser fUser = mAuth.getCurrentUser();
                            moveToNext();
                            PreferenceUtil.setValue(SignUp.this, "유저이메일", email);
                            PreferenceUtil.setValue(SignUp.this, "패스워드", passsord);
                            tempKey = email.replace(".", "_");
//                            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SignUp.this, new OnSuccessListener<InstanceIdResult>(){
//                                @Override
//                                public void onSuccess(InstanceIdResult instanceIdResult) {
//                                    String refreshedToken = instanceIdResult.getToken();
//                                    Log.e("newToken", refreshedToken);
//                                    User user = new User(fUser.getUid(), email, refreshedToken);
//                                    userRef.child(tempKey).setValue(user);
//                                }
//                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp.this, "확인안됨" + e.toString(), Toast.LENGTH_SHORT).show();
                Log.e("로그", e.toString());
            }
        });
    }


    public void moveToNext() {
        Intent intent = new Intent(SignUp.this, PhoneConnectActivity.class);
        intent.putExtra("tempKey", tempKey);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    boolean checkEmail = false;
    boolean checkPassword = false;

    private void initView() {
        signUp_email_edit = (EditText) findViewById(R.id.signUp_email_edit);
//        signUp_email_edit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                checkEmail = VerificationUtil.isValidEmail(toString());
//                enableSignupButton();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        signUp_password_edit = (EditText) findViewById(R.id.signUp_password_edit);
//        signUp_password_edit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                checkEmail = VerificationUtil.isValidEmail(toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        signUp_btn = (Button) findViewById(R.id.signUp_btn);
    }

//    private void enableSignupButton() {
//        if (checkEmail && checkPassword) {
//            signUp_btn.setEnabled(true);
//        } else {
//            signUp_btn.setEnabled(false);
//        }
//
//    }
}