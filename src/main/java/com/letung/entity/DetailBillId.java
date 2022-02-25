package com.letung.entity;


import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DetailBillId implements Serializable {
    private int idBill;
    private int idDetailProduct;

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdDetailProduct() {
        return idDetailProduct;
    }

    public void setIdDetailProduct(int idDetailProduct) {
        this.idDetailProduct = idDetailProduct;
    }
}
