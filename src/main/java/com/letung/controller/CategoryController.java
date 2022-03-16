package com.letung.controller;


import com.letung.entity.Product;
import com.letung.entity.ProductCategory;
import com.letung.service.CategoryService;
import com.letung.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("category/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("{id}/{nameCategory}")
    public String Default(ModelMap modelMap, @PathVariable int id, @PathVariable String nameCategory){
        List<ProductCategory> listCategory = categoryService.listProductCategory();
        List<Product> listProductByCategory = productService.getListProductByCategory(id);
        modelMap.addAttribute("listCategory", listCategory);
        modelMap.addAttribute("listProductByCategory", listProductByCategory);
        modelMap.addAttribute("nameCategory", nameCategory);
        return "category";
    }
}
