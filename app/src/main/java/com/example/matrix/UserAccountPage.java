package com.example.matrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserAccountPage extends AppCompatActivity  {
    private TextView userNameTV, userEmailTV;
    private Button logOutButton, deleteAccountButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_page);
        userNameTV = findViewById(R.id.userAccountNameTV);
        userEmailTV = findViewById(R.id.userAccountEmailTV);
        logOutButton = findViewById(R.id.logOutButton);
        deleteAccountButton = findViewById(R.id.deleteAccountButton);

        String fullName = getIntent().getStringExtra("fName");
        String email = getIntent().getStringExtra("email");
        userNameTV.setText(fullName);
        userEmailTV.setText(email);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserAccountPage.this, MainActivity.class));
            }
        });
        
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserAccountPage.this, "Under Dev", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void backFromAccount(View view) {
        startActivity(new Intent(UserAccountPage.this, Main_Page.class));
    }
}