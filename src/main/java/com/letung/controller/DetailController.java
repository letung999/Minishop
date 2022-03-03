package com.letung.controller;


import com.letung.entity.Cart;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("detail/")
@SessionAttributes("cart")
public class DetailController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("{idProduct}")
    public String Default(@PathVariable int idProduct, ModelMap modelMap, HttpSession httpSession){
        Product product = productService.getProductById(idProduct);
        List<ProductCategory> listCategory = categoryService.listProductCategory();
        if(httpSession.getAttribute("cart") != null){
            List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
            modelMap.addAttribute("quantity", cartList.size());
        }
        modelMap.addAttribute("productById", product);
        modelMap.addAttribute("listCategory", listCategory);
        return "detail";
    }
}
