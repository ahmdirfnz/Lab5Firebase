package com.example.lab5firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    private Button btn2_signup;
    private EditText userName, passWord;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.username_signUp);
        passWord = findViewById(R.id.password_signUp);
        btn2_signup = findViewById(R.id.sign);

        mAuth = FirebaseAuth.getInstance();

        btn2_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userName.getText().toString();
                String password = passWord.getText().toString();

                if (email.isEmpty()) {
                    userName.setError("Email is empty gampang");
                    userName.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    userName.setError("Enter the valid email address gampang");
                    userName.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    passWord.setError("Enter the password");
                    passWord.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    passWord.setError("Length of your password must be more than 6 haiyaa");
                    passWord.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "You are successfully registered", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "You are not registered yet, try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}