//package com.example.mombo;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.content.Intent;
//import android.os.UserManager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//
//import com.kakao.auth.ErrorCode;
//import com.kakao.auth.ISessionCallback;
//import com.kakao.auth.Session;
//import com.kakao.network.ErrorResult;
//import com.kakao.usermgmt.UserManagement;
//import com.kakao.usermgmt.callback.LogoutResponseCallback;
//import com.kakao.usermgmt.callback.MeResponseCallback;
//import com.kakao.usermgmt.response.model.UserProfile;
//import com.kakao.util.exception.KakaoException;
//import com.kakao.util.helper.log.Logger;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    SessionCallback callback;
//
//    UserManagement.requestLogout(new LogoutResponseCallback() {
//        @Override
//        public void onCompleteLogout() {
//            //로그아웃 성공 후 하고싶은 내용 코딩 ~
//        }
//    });
//
//    callback = new SessionCallback();
//        Session.getCurrentSession().addCallback(callback);
//
//}
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //간편로그인시 호출 ,없으면 간편로그인시 로그인 성공화면으로 넘어가지 않음
//        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
//            return;
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//private class SessionCallback implements ISessionCallback {
//
//    @Override
//    public void onSessionOpened() {
//
//        UserManagement.requestMe(new MeResponseCallback() {
//
//            @Override
//            public void onFailure(ErrorResult errorResult) {
//                String message = "failed to get user info. msg=" + errorResult;
//                Logger.d(message);
//
//                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
//                if (result == ErrorCode.CLIENT_ERROR_CODE) {
//                    finish();
//                } else {
//                    //redirectMainActivity();
//                }
//            }
//
//            @Override
//            public void onSessionClosed(ErrorResult errorResult) {
//            }
//
//            @Override
//            public void onNotSignedUp() {
//            }
//
//            @Override
//            public void onSuccess(UserProfile userProfile) {
//                //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
//                //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.
//                Log.e("UserProfile", userProfile.toString());
//                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//    }
//
//    @Override
//    public void onSessionOpenFailed(KakaoException exception) {
//        // 세션 연결이 실패했을때
//        // 어쩔때 실패되는지는 테스트를 안해보았음 ㅜㅜ
//    }
//}
//}
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        Button Button1 = (Button) findViewById(R.id.button);
//        Button1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//
//    }
//}
