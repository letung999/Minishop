package com.letung.dao;

import com.letung.daoImpl.CategoryImpl;
import com.letung.entity.ProductCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryDAO implements CategoryImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<ProductCategory> listProductCategory() {
        List<ProductCategory> listCategory = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        listCategory = session.createQuery("from productcategory ").getResultList();
        return listCategory;
    }
}
