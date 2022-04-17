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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
    private EditText resetEmailED;
    private Button resetButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        resetButton = findViewById(R.id.resetButton);
        resetEmailED = findViewById(R.id.emailForgotPassword);
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }


    private void resetPassword() {
        String email = resetEmailED.getText().toString().trim();

        if (email.isEmpty()) {
            resetEmailED.setError("Email is required!");
            resetEmailED.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            resetEmailED.setError("Please enter the valid email!");
            resetEmailED.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Forgot_Password.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Forgot_Password.this, "Failed to reset password! Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void backFromForgetPass(View view) {
        startActivity(new Intent(Forgot_Password.this, sign_In.class));
    }
}