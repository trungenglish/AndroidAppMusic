package com.example.meidiamusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meidiamusic.fragment.HomeFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String DATABASE_NAME = "Appmusic";
    String DB_SUFFIX_PATH = "/Appmusic/";
    static SQLiteDatabase database =  null;
    TextView txtLogin;
    Button btnSignUp,btnSignUpPhoneNumber,BtnSignUpGoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLogin = findViewById(R.id.txtLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUpPhoneNumber = findViewById(R.id.btnSignUpPhoneNumber);
        BtnSignUpGoogle = findViewById(R.id.btnSignUpGoogle);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignInActivity.class);
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
        BtnSignUpGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpGmailActivity.class);
                startActivity(i);
            }
        });
        proccessCopy();
    }
    public String getDatabasePath(){
        return getApplicationInfo().dataDir+DB_SUFFIX_PATH+DATABASE_NAME;
    }
    private void proccessCopy() {
        try {
            File file = getDatabasePath(DATABASE_NAME);
            if (!file.exists()) {
                copyDatabaseFromAssest();
                //Toast.makeText(this, "Copy Database Successfull", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(this,"Copy Database Fail",Toast.LENGTH_SHORT).show();
        }
    }

    private void copyDatabaseFromAssest() {
        try {
            InputStream inputFile = getAssets().open(DATABASE_NAME);
            String outputFileName = getDatabasePath();
            File file = new File(getApplicationInfo().dataDir+DB_SUFFIX_PATH);
            if (!file.exists())
                file.mkdir();
            OutputStream outFile = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length=inputFile.read(buffer))>0)
                outFile.write(buffer,0,length);
            outFile.flush();
            outFile.close();
            inputFile.close();
        }
        catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }
}