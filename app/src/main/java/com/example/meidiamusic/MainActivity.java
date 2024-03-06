package com.example.meidiamusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.meidiamusic.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    TextView txtLogin;
    Button btnSignUp,btnSignUpPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLogin = findViewById(R.id.txtLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUpPhoneNumber = findViewById(R.id.btnSignUpPhoneNumber);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(i);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpFreeActivity.class);
                startActivity(i);
            }
        });
        btnSignUpPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpPhoneNumberActivity.class);
                startActivity(i);
            }
        });

    }
}