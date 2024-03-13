package com.example.meidiamusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meidiamusic.classes.ProgressHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpGmailActivity extends AppCompatActivity {
    ImageView clicBack;
    Button btnContinue;
    EditText edtEmail,edtFullName,edtPass,edtConfirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_gmail);
        clicBack = findViewById(R.id.clickBack);
        btnContinue = findViewById(R.id.btnContinue);
        edtEmail = findViewById(R.id.edtEmail);
        btnContinue = findViewById(R.id.btnContinue);
        edtFullName = findViewById(R.id.edtFullName);
        edtPass = findViewById(R.id.edtPass);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);

        clicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpGmailActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput() == true){
                    ProgressHelper.showDialog(SignUpGmailActivity.this,"Loading...");
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPass.getText().toString())
                            .addOnCompleteListener(SignUpGmailActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("FirebaseAuthen", "createUserWithEmail:success");
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        //updateUI(user);
                                        gotoMainInActivity();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("FirebaseAuthen", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUpGmailActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });
                }
            }
        });
    }
    void gotoMainInActivity(){
        Intent i = new Intent(SignUpGmailActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
    boolean checkInput() {
        if (edtEmail.getText().toString().length() < 6) {
            edtEmail.setError("Invalid Email.");
            return false;
        }

        if (edtFullName.getText().toString().length() < 3) {
            edtFullName.setError("Invalid full name.");
            return false;
        }

        if (edtPass.getText().toString().length() < 3) {
            edtPass.setError("Invalid password.");
            return false;
        }

        if (!edtPass.getText().toString().equals(edtConfirmPass.getText().toString())) {
            edtConfirmPass.setError("Password not match.");
            return false;
        }

        return true;
    }
}