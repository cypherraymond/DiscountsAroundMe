package com.tl.discountsaroundme.Entities;


public class Item {
    private String name;
    private String type;
    private String description;
    private double price;
    private double discount;
//    Leave it alone for now
    private String picture;

    public Item(String name, String type, double price, double discount) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.discount = discount;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }
}