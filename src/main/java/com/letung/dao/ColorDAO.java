package com.letung.dao;

import com.letung.daoImpl.ColorImpl;
import com.letung.entity.ColorProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ColorDAO implements ColorImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<ColorProduct> getColorList() {
        Session session = sessionFactory.getCurrentSession();
        List<ColorProduct> colorList = session.createQuery("from colorProduct ").getResultList();
        return colorList;
    }
}
