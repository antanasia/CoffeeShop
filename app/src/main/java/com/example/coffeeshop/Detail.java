package com.example.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class Detail extends AppCompatActivity {

    ImageView coffeeImage;
    TextView coffeeName, coffeeDescription, coffeePrice;
    Button submitOrder;

    String[] coffeeNames = {"Американо", "Капучино", "Коктейль", "Фраппучино"};
    String[] coffeePrices = {"$5", "$5", "$5", "$5"};
    String[] coffeeDescriptions = {"Кофе американо – это классический эспрессо, в который добавляется горячая вода. В отличие от другого кофейного напитка, лунго, в американо дополнительная вода не проходит через кофейную таблетку, а доливается уже в готовый напиток.", "Капучи́но — кофейный напиток итальянской кухни на основе эспрессо с добавлением в него подогретого вспененного молока.", "Классический молочный коктйель с различными вкусами: ваниль, школад, банан, орео, клубника", "Фраппучино - это кофейный напиток со льдом. Он состоит из кофейной или кремовой основы, смешанной со льдом и такими ингредиентами, как ароматизированные сиропы, и обычно посыпается взбитыми сливками и / или специями."};
    int[] coffeeImages = {R.drawable.coffee1, R.drawable.coffee2, R.drawable.coffee3, R.drawable.coffee4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        coffeeImage = findViewById(R.id.coffee_image);
        coffeeName = findViewById(R.id.coffee_name);
        coffeeDescription = findViewById(R.id.coffee_description);
        coffeePrice = findViewById(R.id.coffee_price);
        submitOrder = findViewById(R.id.submit_order);

        int getId = getIntent().getIntExtra("id", 0);
        coffeeImage.setImageResource(coffeeImages[getId]);
        coffeeName.setText(coffeeNames[getId]);
        coffeeDescription.setText(coffeeDescriptions[getId]);
        coffeePrice.setText(coffeePrices[getId]);

        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail.this, Submit.class);
                startActivity(intent);
            }
        });
    }

}