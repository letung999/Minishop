package com.letung.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "productcategory")

public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;
    private String nameCategory;
    private String photo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCategory")
    Set<Product> listProduct;

    public Set<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(Set<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}