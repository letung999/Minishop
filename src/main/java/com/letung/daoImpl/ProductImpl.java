package com.letung.daoImpl;

import com.letung.entity.Product;

import java.util.List;

public interface ProductImpl {
    List<Product> getListProductLimit(int startProduct);
    Product getProductById(int idProduct);
    List<Product> getListProductByCategory(int idCategory);
    boolean deleteProductById(int idProduct);
    boolean addProduct(Product product);
    /**/
    boolean updateProduct(Product product);
}
