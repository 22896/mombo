package com.example.mombo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mombo.SignUp.Join_mom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AcceptActivity extends AppCompatActivity {
    Button download_btn;
    EditText myNum_edit;
    EditText friendNum_edit;
    EditText time_edit;
    FirebaseDatabase mDatabase;
    private DatabaseReference userRef;
    DatabaseReference myNumRef;
//    DatabaseReference photoRef;

    String myNumber;
    String friendNumber;
    String tempkey;
    CountDownTimer countDown = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);
        Intent intent = getIntent();
        myNumber = intent.getStringExtra("myNumber");
        friendNumber = intent.getStringExtra("friendNumber");
        tempkey = intent.getStringExtra("tempkey2");

        mDatabase = FirebaseDatabase.getInstance();
        userRef = mDatabase.getReference("UserData");
        myNumRef = mDatabase.getReference("temp").child(myNumber).child("status");
        Toast.makeText(this, myNumber, Toast.LENGTH_SHORT).show();
        download_btn = (Button) findViewById(R.id.download_btn);
        myNum_edit = (EditText) findViewById(R.id.myNum_edit);
        friendNum_edit = (EditText) findViewById(R.id.friendNum_edit);
        time_edit = (EditText) findViewById(R.id.time_edit);
        timer();

        goToProfileActivity();
    }

    public void downloadLink(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = "https://play.google.com/store/apps/details?id=kr.co.vcnc.android.couple";
        intent.putExtra(Intent.EXTRA_TEXT, text);

        Intent chooser = Intent.createChooser(intent, "친구에게 공유하기");
        startActivity(chooser);
    }

    public void goToProfileActivity() {
        myNumRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if ("yes".equals(snapshot.getValue(String.class))) {
                        Intent intent = new Intent(AcceptActivity.this, Join_mom.class);
                        intent.putExtra("tempkey2", tempkey);
                        Toast.makeText(AcceptActivity.this, "연결되었습니다", Toast.LENGTH_SHORT).show();
                        countDown.cancel();
                        startActivity(intent);
                    }
                    Toast.makeText(AcceptActivity.this, snapshot.getValue(String.class), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    String hours;
    private void timer() {
        countDown = new CountDownTimer(1000 * 60 * 60 * 24, 100) {
            @Override
            public void onTick(long mili) {

                String seconds = "" + ((mili / 1000) % 60);            //초
                String minutes = "" + (((mili / (1000 * 60)) % 60));  //분
                String hours = "" + (((mili / (1000 * 60 * 60)) % 24));//시
                time_edit.setText(hours + " : " + minutes + " : " + seconds);
                Log.e("onTick", "시간이 흐른다!");
            }

            @Override
            public void onFinish() {
                Log.e("onFinish", "시간이 멈춘다!");
                if (hours == "0") {
                    DialogUtil.showDialog("제한 시간이 지났습니다.", AcceptActivity.this, false);
                }
            }
        }.start();
    }
}