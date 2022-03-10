package com.letung.service;

import com.letung.dao.BillDAO;
import com.letung.daoImpl.BillImpl;
import com.letung.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService implements BillImpl {

    @Autowired
    BillDAO billDAO;
    @Override
    public int addBill(Bill bill) {
        return billDAO.addBill(bill);
    }
}
