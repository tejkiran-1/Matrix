package com.example.matrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main_Page extends AppCompatActivity {
    private ImageView userAccountIcon;
    private TextView welcomeUser;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        userAccountIcon = findViewById(R.id.userAccountImageView);
        welcomeUser = findViewById(R.id.mainPage_UserWelcomeTV);

        Intent intent = new Intent(Main_Page.this, UserAccountPage.class);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile !=  null) {
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    intent.putExtra("fName", fullName);
                    intent.putExtra("email", email);
                    String[] name = fullName.split(" ");

                    welcomeUser.setText("Hello, " +  name[0] + "!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Main_Page.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });


        userAccountIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


















    }

    public void currentEducationOnClick(View view) {
    }

    public void careerPathSelected(View view) {
        String tag = view.getTag().toString();
        switch (tag) {
            case "scienceCPath":
                startActivity(new Intent(Main_Page.this, SelectedCareerPath.class));
        }
    }
}