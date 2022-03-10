package com.letung.entity;

import javax.persistence.*;

@Entity(name = "detailbill")
public class DetailBill {
    @EmbeddedId
    DetailBillId detailBillId;

    private int quantity;
    private String price;

    public DetailBillId getDetailBillId() {
        return detailBillId;
    }

    public void setDetailBillId(DetailBillId detailBillId) {
        this.detailBillId = detailBillId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
