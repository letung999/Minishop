package com.letung.daoImpl;

import com.letung.entity.Product;

import java.util.List;

public interface ProductImpl {
    List<Product> getListProductLimit(int startProduct);
    Product getProductById(int idProduct);
}
