package com.letung.controller;


import com.letung.dao.CategoryDAO;
import com.letung.dao.ColorDAO;
import com.letung.entity.ColorProduct;
import com.letung.entity.Product;
import com.letung.entity.ProductCategory;
import com.letung.entity.Size;
import com.letung.service.CategoryService;
import com.letung.service.ColorService;
import com.letung.service.ProductService;
import com.letung.service.SizeService;
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

    @Autowired
    CategoryService categoryService;

    @Autowired
    ColorService colorService;

    @Autowired
    SizeService sizeService;


    @GetMapping
    public String Default(ModelMap modelMap){
        List<Product> listProduct = productService.getListProductLimit(0);
        List<Product> allProduct = productService.getListProductLimit(-1);
        List<ProductCategory> listCategory = categoryService.listProductCategory();
        List<ColorProduct> listColorProduct =  colorService.getColorList();
        List<Size> listSizeProduct = sizeService.getSizeList();

        float totalPage = (float) (1.0 * allProduct.size() / 5);
        if(totalPage % 2 != 0){
            totalPage = totalPage + 1;
        }
        System.out.println(totalPage);
        modelMap.addAttribute("listProduct", listProduct);
        modelMap.addAttribute("listCategory", listCategory);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("allProduct", allProduct);
        modelMap.addAttribute("listColorProduct", listColorProduct);
        modelMap.addAttribute("listSizeProduct", listSizeProduct);
        return "addProduct";
    }
}
