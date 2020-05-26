package com.example.mombo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class call extends AppCompatActivity implements View.OnClickListener {

    private Button mCall;
    private Button mDialog;
    private EditText mEditNumber;
    private String mNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        mCall = (Button) findViewById(R.id.BtnCall);
        mDialog = (Button) findViewById((R.id.BtnDialog));
        mEditNumber = (EditText) findViewById((R.id.edtNum));

        mCall.setOnClickListener(this);
        mDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mNum = mEditNumber.getText().toString();
        String tel = "tel:" + mNum;
        switch (v.getId()){
            case R.id.BtnCall:
                startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                break;
            case R.id.BtnDialog:
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                break;
        }
    }
}