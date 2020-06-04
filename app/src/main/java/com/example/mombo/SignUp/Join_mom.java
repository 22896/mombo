package com.example.mombo.SignUp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mombo.Main.ChattingActivity;
import com.example.mombo.Main.FirstActivity;
import com.example.mombo.PreferenceUtil;
import com.example.mombo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class Join_mom extends AppCompatActivity {


    String tempkey;
    private Button start_btn;
    EditText name_edit;
    RadioGroup radiogender;
    RadioButton radiomale, radiofemale;
    FirebaseDatabase mDatabase;
    private DatabaseReference userRef;
    private StorageReference storageRef;
    String gender;
    String name;
    EditText steps;
    EditText hosnum;
    int SelectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mDatabase = FirebaseDatabase.getInstance();
        userRef = mDatabase.getReference("UserData");
        String myNum = PreferenceUtil.getStringValue(this, "myNum");
        storageRef = FirebaseStorage.getInstance().getReference();

        getIntentFromPhoneConnectActivity();

        start_btn = findViewById(R.id.start_btn);
        radiogender = (RadioGroup) findViewById(R.id.radiogender);
        name_edit = (EditText) findViewById(R.id.name_edit);
        hosnum = (EditText) findViewById(R.id.hosnum);
        steps = (EditText) findViewById(R.id.steps);
    }

    public void movetohome(View view) {
        addDatabase();
        Intent intentForHome = new Intent(Join_mom.this, FirstActivity.class);
        intentForHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentForHome);
        Intent intentForChat = new Intent(Join_mom.this, ChattingActivity.class);
        intentForChat.putExtra("tempkey", tempkey);
        startActivity(intentForChat);
    }

    public void addDatabase() {
        SelectedId = radiogender.getCheckedRadioButtonId();
        gender = SelectedId == R.id.radiomale ? "male" : "female";
        name = name_edit.toString().trim();
        userRef.child(tempkey).child("name").setValue(name);
        userRef.child(tempkey).child("hosnum").setValue(hosnum);
        userRef.child(tempkey).child("steps").setValue(steps);
        userRef.child(tempkey).child("gender").setValue(gender);
        PreferenceUtil.setValue(Join_mom.this, "autoSignin", "true");
    }

    public void getIntentFromPhoneConnectActivity() {
        Intent intent = getIntent();
        tempkey = getIntent().getExtras().getString("tempkey2");
    }

}




