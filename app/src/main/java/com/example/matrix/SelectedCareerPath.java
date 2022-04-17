package com.example.matrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectedCareerPath extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_career_path);
    }

    public void backFromSelectedCareerPath(View view) {
        startActivity(new Intent(SelectedCareerPath.this, Main_Page.class));
    }
}