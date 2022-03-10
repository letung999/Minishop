package com.letung.service;

import com.letung.dao.DetailBillDAO;
import com.letung.daoImpl.DetailBillImpl;
import com.letung.entity.DetailBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetailBillService implements DetailBillImpl {
    @Autowired
    DetailBillDAO detailBillDAO;

    @Override
    public boolean addDetailBill(DetailBill detailBill) {
        return detailBillDAO.addDetailBill(detailBill);
    }
}
