package com.letung.service;


import com.letung.dao.CategoryDAO;
import com.letung.daoImpl.CategoryImpl;
import com.letung.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryImpl {

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<ProductCategory> listProductCategory() {
        return categoryDAO.listProductCategory();
    }
}
