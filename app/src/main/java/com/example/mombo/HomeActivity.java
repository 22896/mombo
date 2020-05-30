package com.example.mombo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mombo.chatting.ChattingActivity;

@SuppressWarnings("deprecation")
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvAddHomeAnniversary;
    ConstraintLayout containerNames;
    ConstraintLayout containerViewContents;
    ImageButton btnCommonHome;
    ImageButton btnCommonCamera;
    ImageButton btnCommonChatting;
    ImageButton btnCommonRecord;
    ImageView ivBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initListener();
        initBackground();
//        initFragment();
    }

    private void initBackground() {
        ivBackground=(ImageView)findViewById(R.id.imageView);
    }

    public void initFragment(){
        Log.e("---------Activity----","  [Add] ListFragment()-----");
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.container, new MainFragment())    // ListFragment.onAttach(this)인 셈
//                .commit();
        Log.e("---------Activity----","  [Commit] ListFragment()-----");

    }
    public void initListener(){
        tvAddHomeAnniversary.setOnClickListener(this);
        containerViewContents.setOnClickListener(this);
        containerNames.setOnClickListener(this);
        btnCommonHome.setOnClickListener(this);
        btnCommonCamera.setOnClickListener(this);
        btnCommonChatting.setOnClickListener(this);
        btnCommonRecord.setOnClickListener(this);
    }

    public void initView(){
        // 등록버튼[+버튼 클릭시], 기념일 클릭시 리스트 이동, 이름들 선택시 리스트 버튼
//        tvAddHomeAnniversary = (TextView) findViewById(R.id.tvAddHomeAnniversary);
//        containerViewContents= (ConstraintLayout) findViewById(R.id.containerViewContents);
//        containerNames       = (ConstraintLayout) findViewById(R.id.containerNames);
        btnCommonHome        = (ImageButton) findViewById(R.id.btnCommonHome);
        btnCommonCamera      = (ImageButton) findViewById(R.id.btnCommonCamera);
        btnCommonChatting    = (ImageButton) findViewById(R.id.btnCommonChatting);
        btnCommonRecord    = (ImageButton) findViewById(R.id.btnCommonRecord);
    }

    public static final int SELECT_IMAGE=1003;
    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch(id){
//            case R.id.tvAddHomeAnniversary :
//                startActivity(new Intent(HomeActivity.this, AnniversaryListActivity.class));
//                Toast.makeText(HomeActivity.this, "기념일 누름",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.containerViewContents :
//            case R.id.containerNames :
//                startActivity(new Intent(HomeActivity.this, CalendarWriteActivity.class));
//                Toast.makeText(HomeActivity.this, "add 기념일 누름",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.btnCommonHome :
                btnCommonHome.setImageResource(R.drawable.home_on);
//                btnCommonMore.setImageResource(R.drawable.my_off);
                break;
            case R.id.btnCommonCamera :
//                btnCommonHome.setImageResource(R.drawable.home_off);
//                btnCommonMore.setImageResource(R.drawable.my_on);
                startActivity(new Intent(HomeActivity.this, CameraActivity.class));
                break;
            case R.id.btnCommonChatting:
                startActivity(new Intent(HomeActivity.this, ChattingActivity.class));
                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if (data != null) {
                    try {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String filePath = cursor.getString(columnIndex);
                        cursor.close();

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        ivBackground.setImageBitmap(bitmap);

                        saveDB();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveDB() {

    }
}
