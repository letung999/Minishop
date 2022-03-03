package com.letung.controller;


import com.letung.entity.Cart;
import com.letung.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("api/")
@SessionAttributes({"email", "cart"})
public class APIController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("checkLogin")
    @ResponseBody
    public String checkLogin(@RequestParam String email, @RequestParam String password, ModelMap modelMap) {
        boolean check = employeeService.checkLogin(email, password);
        modelMap.addAttribute("email", email);
        return "" + check;
    }

    @GetMapping("addCart")
    @ResponseBody
    public void addProductInCart(@RequestParam int idDetailProduct, @RequestParam int idProduct
            , @RequestParam int idColor, @RequestParam int idSize
            , @RequestParam String nameProduct, @RequestParam String price
            , @RequestParam String nameColor, @RequestParam String nameSize, @RequestParam int quantity, HttpSession httpSession) {
        if (httpSession.getAttribute("cart") == null) {
            List<Cart> cartList = new ArrayList<>();
            Cart cart = new Cart(idDetailProduct, idProduct, idSize, idColor, nameProduct, price, nameColor, nameSize, 1);
            cartList.add(cart);
            httpSession.setAttribute("cart", cartList);
        } else {
            List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
            int index = checkExitProduct(idProduct, idSize, idColor, httpSession);
            if (index == -1) {
                Cart cart = new Cart(idDetailProduct, idProduct, idSize, idColor, nameProduct, price, nameColor, nameSize, 1);
                cartList.add(cart);
            } else {
                int newQuantity = cartList.get(index).getQuantity() + 1;
                cartList.get(index).setQuantity(newQuantity);
            }
        }

    }

    private int checkExitProduct(int idProduct, int idSize, int idColor, HttpSession httpSession) {
        List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
        for (int i = 0; i < cartList.size(); ++i) {
            if (cartList.get(i).getIdSize() == idSize && cartList.get(i).getIdProduct() == idProduct
                    && cartList.get(i).getIdColor() == idColor) {
                return i;
            }
        }
        return -1;
    }

    @GetMapping("getQuantityInCart")
    @ResponseBody
    public String getQuantityInCart(HttpSession httpSession){
        if(httpSession.getAttribute("cart") != null){
            List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
            return "" + cartList.size();
        }
        return "";
    }

}
