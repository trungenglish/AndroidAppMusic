package com.example.meidiamusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInActivity extends AppCompatActivity {
    ImageView clickBack;
    Button btnContinue;
    EditText edtEmailOrUsername,edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        clickBack = findViewById(R.id.clickBack);
        btnContinue = findViewById(R.id.btnContinue);
        edtEmailOrUsername = findViewById(R.id.edtEmailOrUsername);
        edtPass = findViewById(R.id.edtPass);

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        //Sign In With Email Or Username
//        String emailOrUsername = edtEmailOrUsername.getText().toString();
//        String pass = edtPass.getText().toString();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtEmailOrUsername.getText().toString()) || TextUtils.isEmpty(edtPass.getText().toString())){
                    Toast.makeText(SignInActivity.this,"Không để trống mật khẩu và tài khoản",Toast.LENGTH_LONG).show();
                }
                else {
                    if (isValidEmail(edtEmailOrUsername.getText().toString())){
                        SignInWithEmail();
                    }else{
                        SignInWithUsername();
                    }
                }
            }
        });

    }
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void SignInWithEmail(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(edtEmailOrUsername.getText().toString(),edtPass.getText().toString())
                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FirebaseAuthen", "signInWithEmail:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            gotoHomePageActivity();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FirebaseAuthen", "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
    private void SignInWithUsername(){
        FirebaseFirestore.getInstance().collection("users")
                .whereEqualTo("username",edtEmailOrUsername.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Kiểm tra xem mật khẩu có khớp không
                                if (document.getString("pass").equals(edtPass.getText().toString())) {
                                    // Mật khẩu khớp, đăng nhập thành công
                                    Toast.makeText(SignInActivity.this,"Success Password",
                                            Toast.LENGTH_LONG).show();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            gotoHomePageActivity();
                                        }
                                    },2000);
                                } else {
                                    // Mật khẩu không khớp, đăng nhập thất bại
                                    Toast.makeText(SignInActivity.this,"Wrong Password",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Log.d("FirebaseStore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    void gotoHomePageActivity(){
        Intent i = new Intent(SignInActivity.this,HomePageActivity.class);
        startActivity(i);
    }
}