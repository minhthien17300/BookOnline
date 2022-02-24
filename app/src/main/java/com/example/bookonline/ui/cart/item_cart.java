package com.example.bookonline.ui.cart;

public class item_cart {
    public int img;
    public String name;
    public int price;
    public int quantity;
    public int total;

    public item_cart() {
    }

    public item_cart(int img, String name, int price, int quantity, int total) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
