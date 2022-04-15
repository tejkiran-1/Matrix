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
import com.google.firebase.auth.FirebaseUser;

public class sign_In extends AppCompatActivity {
    private Button signInButton;
    private EditText signInEmail, signInPassword;
    TextView resetPasswordTV;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInButton = findViewById(R.id.signInSignInButton);
        signInEmail = findViewById(R.id.emailSignIn);
        signInPassword = findViewById(R.id.passwordSignIn);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        resetPasswordTV = findViewById(R.id.resetPassword);

        mAuth = FirebaseAuth.getInstance();


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });

    }

    private void signInUser() {
        String userEmail = signInEmail.getText().toString().trim();
        String userPassword = signInPassword.getText().toString().toString();

        if (userEmail.isEmpty()) {
            signInEmail.setError("Please enter registered email!");
            signInEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            signInEmail.setError("Enter a valid email!");
            signInEmail.requestFocus();
            return;
        }
        if (userPassword.isEmpty()) {
            signInPassword.setError("Password is required!");
            signInPassword.requestFocus();
            return;
        }
        if (userPassword.length() < 6) {
            signInPassword.setError("Password should be 6 or more characters long!");
            signInPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        Toast.makeText(sign_In.this, "Signed-In Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(sign_In.this, Main_Page.class));
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(sign_In.this, "Check your email for verification!", Toast.LENGTH_LONG).show();
                    }
                    //redirect to the user account
                    progressBar.setVisibility(View.INVISIBLE);

                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(sign_In.this, "Sign-In failed! Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void registerText(View view) {
        Intent intent = new Intent(getApplicationContext(), Register_Page.class);
        startActivity(intent);
    }

    public void forgotPasswordTextOnClick(View view) {
        startActivity(new Intent(sign_In.this, Forgot_Password.class));
    }
}