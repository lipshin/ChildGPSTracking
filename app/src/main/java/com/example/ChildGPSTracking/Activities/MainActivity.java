package com.example.ChildGPSTracking.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ChildGPSTracking.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}