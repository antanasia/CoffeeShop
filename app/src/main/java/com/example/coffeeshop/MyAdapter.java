package com.example.coffeeshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.Model.Customers;
import com.example.coffeeshop.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Customers> list;

    public MyAdapter(Context context, ArrayList<Customers> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.customerenrty, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Customers customers = list.get(position);
        holder.name.setText(customers.getName());
        holder.quantity.setText(customers.getCount());
        holder.vanilla.setText(customers.getVanillaTopping());
        holder.chocolate.setText(customers.getChocolateTopping());
        holder.caramel.setText(customers.getCaramelTopping());
        holder.price.setText(customers.getResult());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, vanilla, chocolate, caramel, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text);
            quantity = itemView.findViewById(R.id.quantity_text);
            vanilla = itemView.findViewById(R.id.vanilla_text);
            chocolate = itemView.findViewById(R.id.chocolate_text);
            caramel = itemView.findViewById(R.id.caramel_text);
            price = itemView.findViewById(R.id.price_text);
        }
    }
}
