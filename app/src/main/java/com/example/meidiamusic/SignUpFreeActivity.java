package com.example.meidiamusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.meidiamusic.classes.ProgressHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SignUpFreeActivity extends AppCompatActivity {
    ImageView clickBack,imgAvatar;
    Button btnRegister;
    EditText edtUserName,edtFullName,edtPass,edtConfirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_free);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnRegister = findViewById(R.id.btnRegister);
        clickBack = findViewById(R.id.clickBack);
        edtUserName = findViewById(R.id.edtUserName);
        edtFullName = findViewById(R.id.edtFullName);
        edtPass = findViewById(R.id.edtPass);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);
        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpFreeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                if (checkInput() == true) {
                    ProgressHelper.showDialog(SignUpFreeActivity.this,"Loading...");
                    checkUsernameExist();

                }
                //
            }
        });
    }
    private void checkUsernameExist(){
        Handler handler = new Handler();
        FirebaseFirestore.getInstance().collection("users")
                .whereEqualTo("username",edtUserName.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Lấy kết quả từ truy vấn
                            QuerySnapshot querySnapshot = task.getResult();
                            // Kiểm tra xem kết quả có rỗng hay không
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                // Username đã tồn tại, không thể sử dụng
                                // Thông báo cho người dùng rằng username đã tồn tại
                                Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                // Username chưa tồn tại, có thể sử dụng
                                // Tiếp tục với quá trình đăng ký
                                firebaseRegisterNewUser();
                            }
                        } else {
                            // Xử lý khi có lỗi xảy ra trong quá trình truy vấn
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressHelper.dismissDialog();
            }
        },3000);

    }
    private void firebaseRegisterNewUser(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("username", edtUserName.getText().toString().trim());
        user.put("fullName", edtFullName.getText().toString());
        user.put("pass", edtPass.getText().toString());
        //Check username exist

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        ProgressHelper.dismissDialog();
                        Log.d("SignUpFreeActivity", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        ProgressHelper.dismissDialog();
                        Log.w("SignUpFreeActivity", "Error adding document", e);
                    }
                });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SignUpFreeActivity.this,SignInActivity.class);
                startActivity(i);
            }
        },1000);
    }
    boolean checkInput() {
        if (edtUserName.getText().toString().length() < 6) {
            edtUserName.setError("Invalid username.");
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