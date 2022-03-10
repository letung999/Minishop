package com.letung.dao;

import com.letung.daoImpl.BillImpl;
import com.letung.entity.Bill;
import com.letung.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BillDAO implements BillImpl {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public int addBill(Bill bill) {
        Session session = sessionFactory.getCurrentSession();
        int idBill = (int) session.save(bill);
        return idBill;
    }
}
