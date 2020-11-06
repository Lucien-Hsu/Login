package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String PREF = "PREF";
    static final String PREF_NAME = "PREF_NAME";
    static final String PREF_ID = "PREF_ID";
    static final String PREF_PWD = "PREF_PWD";
    static final String PREF_AGE = "PREF_AGE";

    Context context;
    EditText etID, etPWD;
    Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        //取得元件
        etID = findViewById(R.id.et_login_id);
        etPWD = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_sign_up);

        //登入按鈕
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取得輸入資料
                String id = etID.getText().toString();
                String pwd = etPWD.getText().toString();

                if (id.length() == 0 || pwd.length() == 0) {
                    //若未輸入所有資料則跳出提示
                    Toast.makeText(context, "請輸入帳號與密碼", Toast.LENGTH_SHORT).show();
                } else {
                    //取出sharedPreference資料
                    //以帳號名稱創建一個SharedPreferences，並取出資料
                    SharedPreferences settings = getSharedPreferences(id, 0);
                    String userID = settings.getString(PREF_ID, "");
                    String userPWD = settings.getString(PREF_PWD, "");
                    String userName = settings.getString(PREF_NAME, "");
//                    Log.d("mTest", "MainActivity:userID = " + userID + " userPWD = " + userPWD);

                    if(userID.length() == 0){
                        //若帳號不存在則提示
                        Toast.makeText(context, "帳號不存在", Toast.LENGTH_SHORT).show();
                    }else{
                        //若帳號存在，則比對密碼是否符合，若符合則換頁
                        if (userPWD.equals(etPWD.getText().toString())) {
                            Intent intent = new Intent(context, LoginActivity.class);
                            //將使用者名稱傳出
                            intent.putExtra("name", userName);
                            startActivity(intent);
                        }else{
                            //若密碼不符合則提示
                            Toast.makeText(context, "密碼錯誤", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        //註冊按鈕
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳頁
                Intent intent = new Intent(context, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}