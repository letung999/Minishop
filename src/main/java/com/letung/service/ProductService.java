package com.letung.service;

import com.letung.dao.ProductDAO;
import com.letung.daoImpl.ProductImpl;
import com.letung.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductImpl {

    @Autowired
    ProductDAO productDAO;

    @Override
    public List<Product> getListProductLimit(int startProduct) {
        return productDAO.getListProductLimit(startProduct);
    }

    @Override
    public Product getProductById(int idProduct) {
        return productDAO.getProductById(idProduct);
    }
}
