package com.example.meidiamusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SignUpFreeActivity extends AppCompatActivity {
    ImageView clickBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_free);
        clickBack = findViewById(R.id.clickBack);
        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpFreeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}