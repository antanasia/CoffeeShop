package com.example.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Profile extends AppCompatActivity {

    private Button signOutBtn;
    private ImageView westBtn, listBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        signOutBtn = (Button)findViewById(R.id.btn_signout);
        westBtn = findViewById(R.id.west);
        listBtn = findViewById(R.id.list);


        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signoutIntent = new Intent(Profile.this, Authorization.class);
                startActivity(signoutIntent);
            }
        });

        westBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent westIntent = new Intent(Profile.this, Menu.class);
                startActivity(westIntent);
            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(Profile.this, CustomerList.class);
                startActivity(listIntent);
            }
        });
    }
}