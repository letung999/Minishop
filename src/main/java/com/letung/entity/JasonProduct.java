package com.letung.entity;

import java.util.Set;

public class JasonProduct {
    private int idProduct;
    private String nameProduct;
    private String price;
    private String description;
    private ProductCategory productCategory;
    private String photo;
    private String gender;
    private Set<DetailProduct> listDetailProduct;
    private Set<Sale> listSale;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<DetailProduct> getListDetailProduct() {
        return listDetailProduct;
    }

    public void setListDetailProduct(Set<DetailProduct> listDetailProduct) {
        this.listDetailProduct = listDetailProduct;
    }

    public Set<Sale> getListSale() {
        return listSale;
    }

    public void setListSale(Set<Sale> listSale) {
        this.listSale = listSale;
    }
}
