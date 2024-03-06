package com.example.meidiamusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpPhoneNumberActivity extends AppCompatActivity {
    ImageView clickBack;
    EditText edtSDT;
    Button btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_phone_number);
        clickBack = findViewById(R.id.clickBack);
        edtSDT = findViewById(R.id.edtSDT);
        btnContinue = findViewById(R.id.btnContinue);

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpPhoneNumberActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}