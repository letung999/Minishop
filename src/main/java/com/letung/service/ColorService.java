package com.letung.service;

import com.letung.dao.ColorDAO;
import com.letung.daoImpl.ColorImpl;
import com.letung.entity.ColorProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;


@Service
public class ColorService implements ColorImpl {

    @Autowired
    ColorDAO colorDAO;
    @Override
    public List<ColorProduct> getColorList() {
        return colorDAO.getColorList() ;
    }
}
