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

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    Context context;

    //存所有帳號
//    static final String PREF = "PREF";
    //存每個帳號資料
    ArrayList<String> pref;
    static final String PREF_NAME = "PREF_NAME";
    static final String PREF_ID = "PREF_ID";
    static final String PREF_PWD = "PREF_PWD";
    static final String PREF_AGE = "PREF_AGE";

    EditText etName, etID, etPWD, etAge;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("RegisterActivity");
        Log.d("mTest", "SignUpActivity");

        context = this;

        //取得元件
        etName = findViewById(R.id.et_name);
        etID = findViewById(R.id.et_id);
        etPWD = findViewById(R.id.et_pwd);
        etAge = findViewById(R.id.et_age);
        btnOK = findViewById(R.id.btn_ok);

        //按鈕監聽
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取得輸入資料
                String name = etName.getText().toString();
                String id = etID.getText().toString();
                String pwd = etPWD.getText().toString();
                String age = etAge.getText().toString();

                if (name.isEmpty() || id.isEmpty() || pwd.isEmpty() || age.isEmpty()) {
                    //若有資料未輸入則跳出提醒
                    Toast.makeText(context, "請輸入完整資料", Toast.LENGTH_SHORT).show();
                } else {
                    //依帳號取出儲存資料，若資料中的帳號有內容則提示帳號已存在
                    SharedPreferences settings = getSharedPreferences(id, 0);
                    String userID = settings.getString(PREF_ID, "");
                    if (userID.length() != 0) {
                        Toast.makeText(context, "此帳號已存在", Toast.LENGTH_SHORT).show();
                        Log.d("mTest", "name:" + name + " id:" + id + " pwd:" + pwd + " age:" + age);
                        Log.d("mTest", "settings:" + settings);
                    } else {
                        //若資料不存在則寫入資料
                        settings.edit()
                                .putString(PREF_NAME, name)
                                .putString(PREF_ID, id)
                                .putString(PREF_PWD, pwd)
                                .putString(PREF_AGE, age)
                                .apply();
                        //提示註冊成功
                        Toast.makeText(context, name + " 註冊成功", Toast.LENGTH_SHORT).show();
//                        Log.d("mTest", "name:" + name + " id:" + id + " pwd:" + pwd + " age" + age);

                        //跳頁
                        Intent intent = new Intent(context, LoginActivity.class);
                        //將使用者名稱傳出
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}