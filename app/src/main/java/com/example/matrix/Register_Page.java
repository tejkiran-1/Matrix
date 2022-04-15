package com.example.matrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Register_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
    }

    public void signIn(View view) {
        Intent intent = new Intent(getApplicationContext(), sign_In.class);
        startActivity(intent);
    }

    public void registerText(View view) {
    }
}