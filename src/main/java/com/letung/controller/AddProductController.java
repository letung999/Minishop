package com.letung.controller;


import com.letung.entity.Product;
import com.letung.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("addProduct/")
public class AddProductController {
    @Autowired
    ProductService productService;


    @GetMapping
    public String Default(ModelMap modelMap){
        List<Product> listProduct = productService.getListProductLimit(0);
        List<Product> allProduct = productService.getListProductLimit(-1);
        System.out.println(allProduct.size());
        float totalPage = (float) (1.0 * allProduct.size() / 5);
        if(totalPage % 2 != 0){
            totalPage = totalPage + 1;
        }
        System.out.println(totalPage);
        modelMap.addAttribute("listProduct", listProduct);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("allProduct", allProduct);
        return "addProduct";
    }
}
