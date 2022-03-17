package com.letung.controller;


import com.letung.entity.*;
import com.letung.service.BillService;
import com.letung.service.CategoryService;
import com.letung.service.DetailBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("cart/")
public class CartController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    BillService billService;

    @Autowired
    DetailBillService detailBillService;

    @GetMapping
    public String Default(HttpSession httpSession, ModelMap modelMap) {
        if (httpSession.getAttribute("cart") != null) {
            List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
            String email = (String) httpSession.getAttribute("email");
            String firstWord = email.substring(0, 1).toUpperCase();
            modelMap.addAttribute("firstWord", firstWord);
            modelMap.addAttribute("quantity", cartList.size());
            modelMap.addAttribute("carts", cartList);
        }
        List<ProductCategory> listCategory = categoryService.listProductCategory();
        modelMap.addAttribute("listCategory", listCategory);
        return "cart";
    }

    @PostMapping(produces = "text/plain; charset=UTF-8")
    public String addBill(@RequestParam String nameCustomer, @RequestParam String phoneNumber,
                          @RequestParam String address, @RequestParam String deliveryBy,
                          @RequestParam String note, HttpSession httpSession) {
        if (httpSession.getAttribute("cart") != null) {
            /*addBill*/
            List<Cart> cartList = (List<Cart>) httpSession.getAttribute("cart");
            Bill bill = new Bill();
            bill.setNameClient(nameCustomer);
            bill.setPhoneNumber(phoneNumber);
            bill.setAddress(address);
            bill.setDeliveryBy(deliveryBy);
            bill.setNote(note);
            int idBill = billService.addBill(bill);


            /*add detailBill*/
            if(idBill > 0){
                Set<Bill> listDetailBill = new HashSet<>();
                for (Cart cart:cartList) {
                    DetailBillId detailBillId = new DetailBillId();
                    detailBillId.setIdBill(bill.getIdBill());
                    detailBillId.setIdDetailProduct(cart.getIdDetailProduct());

                    DetailBill detailBill = new DetailBill();
                    detailBill.setDetailBillId(detailBillId);
                    detailBill.setPrice(cart.getPrice());
                    detailBill.setQuantity(cart.getQuantity());
                    detailBillService.addDetailBill(detailBill);

                }

            }
            else{
                System.out.println("thêm thất bại");
            }

        }

        return "cart";
    }
}
