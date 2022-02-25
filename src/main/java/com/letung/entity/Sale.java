package com.letung.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSale;
    private String nameSale;
    private String startTime;
    private String endTime;
    private String description;
    private String photoSale;
    private int decreasePrice;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "detailsale",
            joinColumns = {@JoinColumn(name = "idSale", referencedColumnName = "idSale")},
            inverseJoinColumns = {@JoinColumn(name = "idProduct", referencedColumnName = "idProduct")})
    Set<Product> listProduct;

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public String getNameSale() {
        return nameSale;
    }

    public void setNameSale(String nameSale) {
        this.nameSale = nameSale;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoSale() {
        return photoSale;
    }

    public void setPhotoSale(String photoSale) {
        this.photoSale = photoSale;
    }

    public int getDecreasePrice() {
        return decreasePrice;
    }

    public void setDecreasePrice(int decreasePrice) {
        this.decreasePrice = decreasePrice;
    }

    public Set<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(Set<Product> listProduct) {
        this.listProduct = listProduct;
    }


}
