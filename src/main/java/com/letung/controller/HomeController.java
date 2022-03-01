package com.letung.controller;


import com.letung.entity.Product;
import com.letung.service.ProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ProductService productService;

    @GetMapping
    @Transactional
    public String home(ModelMap modelMap, HttpSession httpSession) {
        if(httpSession.getAttribute("email") != null){
            String email = (String) httpSession.getAttribute("email");
            String firstWord = email.substring(0, 1).toUpperCase();
            modelMap.addAttribute("firstWord", firstWord);
        }

        List<Product> listProduct = productService.getListProductLimit(0);
        modelMap.addAttribute("listProduct", listProduct);
        return "home";

    }


}
