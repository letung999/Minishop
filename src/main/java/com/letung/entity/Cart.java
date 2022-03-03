package com.letung.entity;

public class Cart {
    private int idDetailProduct;
    private int idProduct;
    private int idSize;
    private int idColor;
    private String nameProduct;
    private String price;
    private String nameColor;
    private String nameSize;
    private int quantity;


    public Cart(int idDetailProduct, int idProduct, int idSize,
                int idColor, String nameProduct, String price, String nameColor,
                String nameSize, int quantity) {
        this.idDetailProduct = idDetailProduct;
        this.idProduct = idProduct;
        this.idSize = idSize;
        this.idColor = idColor;
        this.nameProduct = nameProduct;
        this.price = price;
        this.nameColor = nameColor;
        this.nameSize = nameSize;
        this.quantity = quantity;
    }

    public int getIdDetailProduct() {
        return idDetailProduct;
    }

    public void setIdDetailProduct(int idDetailProduct) {
        this.idDetailProduct = idDetailProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }
}
