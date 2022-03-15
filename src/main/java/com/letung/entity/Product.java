package com.letung.entity;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCategory")
    private ProductCategory productCategory;

    private String nameProduct;
    private String price;
    private String description;
    private String photo;
    private String gender;


    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduct")
    private Set<DetailProduct> listDetailProduct;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "detailsale",
            joinColumns = {@JoinColumn(name = "idProduct", referencedColumnName = "idProduct")},
            inverseJoinColumns = {@JoinColumn(name = "idSale", referencedColumnName = "idSale")})
    Set<Sale> listSale;


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }


    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
