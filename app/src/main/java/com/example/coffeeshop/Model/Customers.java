package com.example.coffeeshop.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Customers {
    private String name, count,  result,  vanillaTopping,  chocolateTopping,  caramelTopping;

    public Customers() {

    }

    public Customers(String name, String count, String result, String vanillaTopping, String chocolateTopping, String caramelTopping) {
        this.name = name;
        this.count = count;
        this.result = result;
        this.vanillaTopping = vanillaTopping;
        this.chocolateTopping = chocolateTopping;
        this.caramelTopping = caramelTopping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getVanillaTopping() {
        return vanillaTopping;
    }

    public void setVanillaTopping(String vanillaTopping) {
        this.vanillaTopping = vanillaTopping;
    }

    public String getChocolateTopping() {
        return chocolateTopping;
    }

    public void setChocolateTopping(String chocolateTopping) {
        this.chocolateTopping = chocolateTopping;
    }

    public String getCaramelTopping() {
        return caramelTopping;
    }

    public void setCaramelTopping(String caramelTopping) {
        this.caramelTopping = caramelTopping;
    }
}
