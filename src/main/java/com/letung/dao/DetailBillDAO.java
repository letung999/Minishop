package com.letung.dao;

import com.letung.daoImpl.DetailBillImpl;
import com.letung.entity.DetailBill;
import com.letung.entity.DetailBillId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DetailBillDAO implements DetailBillImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean addDetailBill(DetailBill detailBill) {
        Session session = sessionFactory.getCurrentSession();
        DetailBillId id = (DetailBillId) session.save(detailBill);
        return id != null;
    }
}
