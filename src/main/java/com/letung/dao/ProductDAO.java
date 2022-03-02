package com.letung.dao;


import com.letung.daoImpl.ProductImpl;
import com.letung.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductDAO implements ProductImpl {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Product> getListProductLimit(int startProduct) {
        Session session = sessionFactory.getCurrentSession();
        List<Product>listProduct = new ArrayList<>();
        listProduct = session.createQuery("from product limit").setFirstResult(startProduct).setMaxResults(15).getResultList();
        return listProduct;
    }

    @Override
    @Transactional
    public Product getProductById(int idProduct) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.createQuery("from product where idProduct ='" + idProduct +"'").getSingleResult();
        return product;
    }
}
