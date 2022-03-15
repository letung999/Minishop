package com.letung.service;

import com.letung.dao.SizeDAO;
import com.letung.daoImpl.SizeImpl;
import com.letung.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService implements SizeImpl {

    @Autowired
    SizeDAO sizeDAO;

    @Override
    public List<Size> getSizeList() {
        return sizeDAO.getSizeList();
    }
}
