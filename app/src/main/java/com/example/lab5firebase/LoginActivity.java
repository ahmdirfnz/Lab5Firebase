package com.example.lab5firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText userName, passWord;
    private Button loginBtn, signUpBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.email_login);
        passWord = findViewById(R.id.password_login);

        loginBtn = findViewById(R.id.btn_login);
        signUpBtn = findViewById(R.id.btn_signup);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userName.getText().toString().trim();
                String password = passWord.getText().toString().trim();

                if (email.isEmpty()) {

                    userName.setError("Email is empty la gampang");
                    userName.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    userName.setError("Haiyaa tulis betul2 la");
                    userName.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    passWord.setError("Password is empty");
                    passWord.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    passWord.setError("Length of password is more than 6");
                    passWord.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Please check your login credentials", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signUpBtn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

    }
}