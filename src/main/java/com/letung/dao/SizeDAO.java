package com.letung.dao;


import com.letung.daoImpl.SizeImpl;
import com.letung.entity.Size;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SizeDAO implements SizeImpl {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Size> getSizeList() {
        Session session = sessionFactory.getCurrentSession();
        List<Size> sizeList = session.createQuery("from size").getResultList();
        return sizeList;
    }
}
