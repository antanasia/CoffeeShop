package com.example.coffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coffeeshop.Model.Users;
import com.example.coffeeshop.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Authorization extends AppCompatActivity {

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        Button button1 = (Button) findViewById(R.id.btn_signup);
        Button button2 = (Button) findViewById(R.id.btn_signin);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity1();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity2();
            }
        });

        String UserNumberKey = Paper.book().read(Prevalent.UserNumberKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if(UserNumberKey != "" && UserPasswordKey != "") {
            if(!TextUtils.isEmpty(UserNumberKey) && !TextUtils.isEmpty(UserPasswordKey) ) {
                ValidateUser(UserNumberKey, UserPasswordKey);

                loadingBar.setTitle("Вход в приложение.");
                loadingBar.setMessage("Пожалуйста, подождите...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
    }

    private void ValidateUser(final String number, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(number).exists()) {
                    Users usersData = snapshot.child("Users").child(number).getValue(Users.class);

                    if(usersData.getNumber().equals(number)) {
                        if(usersData.getPassword().equals(password)) {
                            loadingBar.dismiss();
                            Toast.makeText(Authorization.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                            Intent menuIntent = new Intent(Authorization.this, Menu.class);
                            startActivity(menuIntent);
                        }
                        else {
                            loadingBar.dismiss();
                        }
                    }
                }
                else {
                    loadingBar.dismiss();
                    Toast.makeText(Authorization.this, "Аккаунт с номером " + number + " не существует", Toast.LENGTH_SHORT).show();

                    Intent signUpIntent = new Intent(Authorization.this, Signup.class);
                    startActivity(signUpIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void changeActivity1() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    private void changeActivity2() {
        Intent intent1 = new Intent(this, Signin.class);
        startActivity(intent1);
    }
}
