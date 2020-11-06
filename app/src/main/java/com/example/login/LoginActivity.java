package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("WelcomeActivity");
        Log.d("mTest", "LoginActivity");

        //取出intent中的資料
        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");

        //顯示name
        tvShow = findViewById(R.id.tv_show);
        tvShow.setText("Welcome " + userName + ".");
    }
}