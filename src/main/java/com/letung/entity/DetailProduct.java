package com.letung.entity;


import javax.persistence.*;

@Entity(name = "detailproduct")
public class DetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetailProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduct")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSize")
    Size size;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idColor")
    ColorProduct colorProduct;

    private int quantity;
    private String date;

    public int getIdDetailProduct() {
        return idDetailProduct;
    }

    public void setIdDetailProduct(int idDetailProduct) {
        this.idDetailProduct = idDetailProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ColorProduct getColorProduct() {
        return colorProduct;
    }

    public void setColorProduct(ColorProduct colorProduct) {
        this.colorProduct = colorProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
