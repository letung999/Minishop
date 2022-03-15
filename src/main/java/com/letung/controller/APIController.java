package com.letung.controller;


import com.letung.entity.Cart;
import com.letung.entity.Product;
import com.letung.service.EmployeeService;
import com.letung.service.ProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
@RequestMapping("api/")
@SessionAttributes({"email", "cart"})
public class APIController {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProductService productService;


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
    public String getQuantityInCart(HttpSession httpSession) {
        if (httpSession.getAttribute("cart") != null) {
            List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
            return "" + cartList.size();
        }
        return "";
    }

    @GetMapping("updateCart")
    @ResponseBody
    public void updateCart(HttpSession httpSession, @RequestParam int quantity,
                           @RequestParam int idProduct, @RequestParam int idColor,
                           @RequestParam int idSize) {
        if (httpSession.getAttribute("cart") != null) {
            List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
            int index = checkExitProduct(idProduct, idSize, idColor, httpSession);
            cartList.get(index).setQuantity(quantity);
        }
    }

    @GetMapping("deleteCart")
    @ResponseBody
    public void deleteCart(HttpSession httpSession, @RequestParam int idProduct, @RequestParam int idColor,
                           @RequestParam int idSize) {
        if (httpSession.getAttribute("cart") != null) {
            List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
            int index = checkExitProduct(idProduct, idSize, idColor, httpSession);
            cartList.remove(index);
        }
    }

    @GetMapping(path = "getListProductLimit", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String getListproductLimit(@RequestParam int startProduct) {
        String html = "";
        List<Product> listProduct = productService.getListProductLimit(startProduct);
        for (Product product : listProduct) {
            html += "<tr>";
            html += "<td ><div class='checkbox'> <label><input class='checkbox-product' style='width: 25px; height: 25px;' type='checkbox' value='"
                    + product.getIdProduct() + "'></label></div></td>";
            html += "<td class='idProduct' data-idProduct =" + product.getIdProduct() + "'>" + product.getNameProduct()
                    + "</td>";
            html += "<td class='price' data-value =" + product.getPrice() + "'>" + product.getPrice() + "</td>";
            html += "<td class='gender' data-gender =" + product.getGender() + "'>" + product.getGender() + "</td>";
            html += "<td class='updateProduct btn btn-warning' data-id =" + product.getIdProduct() + ">Sửa</td>";
            html += "</tr>";
        }

        return html;
    }

    @GetMapping("deleteProductById")
    @ResponseBody
    public void deleteProduct(@RequestParam int idProduct){
        productService.deleteProductById(idProduct);
    }

    @Autowired
    ServletContext servletContext;

    @PostMapping ("upLoadFile")
    @ResponseBody
    public void upLoadFile(MultipartHttpServletRequest request){
        String pathSaveFile = servletContext.getRealPath("/resources/image/nameStuff/");
        Iterator<String> listName = request.getFileNames();
        MultipartFile multipartFile = request.getFile(listName.next());
        File fileSave = new File(pathSaveFile + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(fileSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pathSaveFile);
    }

}
