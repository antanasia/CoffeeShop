package com.example.coffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.coffeeshop.Model.Users;
import com.example.coffeeshop.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;
import io.paperdb.Paper;

public class Signin extends AppCompatActivity {

    private Button signInBtn;
    private EditText numberSignInInput, passwordSignInInput, userInput;
    private ProgressDialog loadingBar;

    private String parentDbName = "Users";
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signInBtn = (Button)findViewById(R.id.btn_signin);
        numberSignInInput = (EditText)findViewById(R.id.number_signin);
        passwordSignInInput = (EditText)findViewById(R.id.password_signin);
        loadingBar = new ProgressDialog(this);
        checkBoxRememberMe = findViewById(R.id.rememberme);
        Paper.init(this);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinUser();
            }
        });
    }

    private void signinUser() {
        String number = numberSignInInput.getText().toString();
        String password = passwordSignInInput.getText().toString();

        if(TextUtils.isEmpty(number)) {
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }

        else {

            loadingBar.setTitle("Вход в приложение.");
            loadingBar.setMessage("Пожалуйста, подождите...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateUser(number, password);
        }
    }

    private void ValidateUser(String number, String password) {
        if(checkBoxRememberMe.isChecked()) {
            Paper.book().write(Prevalent.UserNumberKey, number);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(number).exists()) {
                    Users usersData = snapshot.child(parentDbName).child(number).getValue(Users.class);

                    if(usersData.getNumber().equals(number)) {
                        if(usersData.getPassword().equals(password)) {
                            loadingBar.dismiss();
                            Toast.makeText(Signin.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                            Intent menuIntent = new Intent(Signin.this, Menu.class);
                            startActivity(menuIntent);
                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(Signin.this, "Неверно введен номер или пароль!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    loadingBar.dismiss();
                    Toast.makeText(Signin.this, "Аккаунт с номером " + number + " не существует", Toast.LENGTH_SHORT).show();

                    Intent signUpIntent = new Intent(Signin.this, Signup.class);
                    startActivity(signUpIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
