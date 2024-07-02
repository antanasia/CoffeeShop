package com.example.coffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.Model.Customers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Submit extends AppCompatActivity implements View.OnClickListener {

    Button add, amount, remove, submitOrder;
    private EditText nameField;
    private CheckBox vanillaCheckBox, chocolateCheckBox, caramelCheckBox;
    DatabaseReference databaseCustomers;

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        nameField = findViewById(R.id.name_field);

        vanillaCheckBox = findViewById(R.id.vanilla);
        chocolateCheckBox = findViewById(R.id.chocolate);
        caramelCheckBox = findViewById(R.id.caramel);

        add = findViewById(R.id.add);
        amount = findViewById(R.id.amount);
        remove = findViewById(R.id.remove);

        add.setOnClickListener(this);
        amount.setOnClickListener(this);
        remove.setOnClickListener(this);

        amount.setText(String.valueOf(quantity));

        submitOrder = findViewById(R.id.submit_order);

        databaseCustomers = FirebaseDatabase.getInstance().getReference();

        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean hasVanillaCheckBox = vanillaCheckBox.isChecked();

                boolean hasChocolateCheckBox = chocolateCheckBox.isChecked();

                boolean hasCaramelCheckBox = caramelCheckBox.isChecked();

                int price = calculatedPrice(hasVanillaCheckBox, hasChocolateCheckBox, hasCaramelCheckBox);

                customerAdd(price, hasVanillaCheckBox, hasChocolateCheckBox, hasCaramelCheckBox);


            }
        });
    }

    private void customerAdd(int price, boolean hasVanillaCheckBox, boolean hasChocolateCheckBox, boolean hasCaramelCheckBox) {
        String name = nameField.getText().toString();
        String count = String.valueOf(quantity);
        String result = String.valueOf(price);
        boolean addVanilla = hasVanillaCheckBox, addChocolate = hasChocolateCheckBox, addCaramel = hasCaramelCheckBox;
        String vanillaTopping = String.valueOf(addVanilla), chocolateTopping = String.valueOf(addChocolate), caramelTopping = String.valueOf(addCaramel);

        if (addVanilla == true) vanillaTopping = "Ванильный сироп";
        else vanillaTopping = "";
        if (addChocolate == true) chocolateTopping = "Шоколадный сироп";
        else chocolateTopping = "";
        if (addCaramel == true) caramelTopping = "Карамельный сироп";
        else caramelTopping = "";


        if (TextUtils.isEmpty(name) || quantity == 0) {
            Toast.makeText(this, "Введите данные!", Toast.LENGTH_SHORT).show();
        } else {
            InsertData(name, count, result, vanillaTopping, chocolateTopping, caramelTopping);
        }
    }

    private int calculatedPrice(boolean addVanilla, boolean addChocolate, boolean addCaramel) {
        int basePrice = 5;

        if (addVanilla) {
            basePrice = basePrice + 1;
        }

        if (addChocolate) {
            basePrice = basePrice + 3;
        }

        if (addCaramel) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                quantity = quantity + 1;
                amount.setText(String.valueOf(quantity));
                break;
            case R.id.remove:
                quantity = quantity - 1;
                String aux = amount.getText().toString();
                if (aux.equals("0")) {
                    quantity = 0;
                }
                amount.setText(String.valueOf(quantity));
                break;
        }
    }

    private void InsertData(String name, String count, String result, String vanillaTopping, String chocolateTopping, String caramelTopping) {

        Customers customers = new Customers(name, count, result, vanillaTopping, chocolateTopping, caramelTopping);
        databaseCustomers.child("customers").child(name).setValue(customers).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Submit.this, "Данные сохранены", Toast.LENGTH_SHORT).show();
                    Intent menuIntent = new Intent(Submit.this, Menu.class);
                    startActivity(menuIntent);
                }
            }
        });
    }
}
