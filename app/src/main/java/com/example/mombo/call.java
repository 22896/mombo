package com.example.mombo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class call extends AppCompatActivity implements View.OnClickListener {

    private Button mCall;
    private Button mDialog;
    private EditText mEditNumber;
    private String mNum;
    private ImageButton mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        mBack = (ImageButton) findViewById(R.id.back);
        mCall = (Button) findViewById(R.id.BtnCall);
        mDialog = (Button) findViewById((R.id.BtnDialog));
        mEditNumber = (EditText) findViewById((R.id.edtNum));

        mCall.setOnClickListener(this);
        mDialog.setOnClickListener(this);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), Mom_Main.class);
                startActivity(intent);
            }
        });

}

    @Override
    public void onClick(View v) {

        mNum = mEditNumber.getText().toString();
        String tel = "tel:" + mNum;
        switch (v.getId()) {
            case R.id.BtnCall:
                startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                break;
            case R.id.BtnDialog:
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                break;
        }
    }
}