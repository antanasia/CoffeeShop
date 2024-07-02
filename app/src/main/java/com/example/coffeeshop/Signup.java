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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    private Button signUpBtn;
    private EditText numberSignUpInput, passwordSignUpInput, userInput;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpBtn = (Button)findViewById(R.id.btn_signup);
        numberSignUpInput = (EditText)findViewById(R.id.number_signup);
        passwordSignUpInput = (EditText)findViewById(R.id.password_signup);
        userInput = (EditText)findViewById(R.id.username);
        loadingBar = new ProgressDialog(this);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String username = userInput.getText().toString();
        String number = numberSignUpInput.getText().toString();
        String password = passwordSignUpInput.getText().toString();

        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(number)) {
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }

        else {
            loadingBar.setTitle("Создание аккаунта");
            loadingBar.setMessage("Пожалуйста, подождите...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhone(username, number, password);
        }
    }

    private void ValidatePhone(String username, String number, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(number).exists())) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("number", number);
                    userDataMap.put("username", username);
                    userDataMap.put("password", password);

                    RootRef.child("Users").child(number).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        loadingBar.dismiss();
                                        Toast.makeText(Signup.this, "Регистрация  прошла успешно.", Toast.LENGTH_SHORT).show();

                                        Intent signinintent = new Intent(Signup.this, Signin.class);
                                        startActivity(signinintent);
                                    }
                                    else {
                                        loadingBar.dismiss();
                                        Toast.makeText(Signup.this, "Ошибка.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(Signup.this, "Номер " + number + " уже зарегистрирован", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent signinintent = new Intent(Signup.this, Signin.class);
                    startActivity(signinintent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}