package com.example.matrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main_Page extends AppCompatActivity {
    private ImageView userAccountIcon;
    private TextView welcomeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        userAccountIcon = findViewById(R.id.userAccountImageView);
        welcomeUser = findViewById(R.id.mainPage_UserWelcomeTV);



        userAccountIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Page.this, UserAccountPage.class));
            }
        });
    }

    public void currentEducationOnClick(View view) {
    }
}