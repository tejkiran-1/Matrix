package com.example.matrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register_Page extends AppCompatActivity {
    private EditText fullNameET, emailET, passwordET, passwordRET;
    private TextView signInText;
    private Button registerButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        fullNameET = findViewById(R.id.userFullName);
        emailET = findViewById(R.id.userEmailReg);
        passwordET = findViewById(R.id.userPasswordReg);
        passwordRET = findViewById(R.id.userPasswordRetype);
        registerButton = findViewById(R.id.registerRegisterButton);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    public void signInOnClick(View view) {
        Intent intent = new Intent(Register_Page.this, sign_In.class);
        startActivity(intent);
    }


    private void registerUser() {
        String fullName = fullNameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();
        String password1 = passwordET.getText().toString().trim();
        String password2 = passwordRET.getText().toString().trim();

        if (fullName.isEmpty()) {
            fullNameET.setError("Full Name is Required!");
            fullNameET.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            emailET.setError("Email-ID is Required!");
            emailET.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("Please provide valid Email-ID!");
            emailET.requestFocus();
            return;
        }
        if (password1.isEmpty()) {
            passwordET.setError("Password is Required!");
            passwordET.requestFocus();
            return;
        }
        if (password1.length() < 6) {
            passwordET.setError("Password length should be 6 or greater characters!");
            passwordET.requestFocus();
            return;
        }

        if (password2.isEmpty()) {
            passwordRET.setError("Password is Required!");
            passwordRET.requestFocus();
            return;
        }
        if (password2.length() < 6) {
            passwordRET.setError("Password length should be 6 or greater characters!");
            passwordRET.requestFocus();
            return;
        }
        if (!password2.equals(password1)) {
            passwordRET.setError("Re-enter the correct password!");
            passwordRET.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullName, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);

                                        //redirect user to the sign in
                                    }
                                    else {
                                        //Toast.makeText(Register_Page.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(Register_Page.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else {
                            //Toast.makeText(getApplicationContext(), "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(Register_Page.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}