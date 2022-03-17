package com.letung.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.letung.entity.*;
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
import java.util.*;


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
            html += "<td class='btn-updateProduct btn btn-warning' data-id =" + product.getIdProduct() + ">Sửa</td>";
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
    ServletContext context;

    @PostMapping("uploadFile")
    @ResponseBody
    public String upLoadFile(MultipartHttpServletRequest request) {
        String pathSaveFile = context.getRealPath("/resources/image/nameStuff/");
        Iterator<String> listFileName = request.getFileNames();
        MultipartFile multipartFile = request.getFile(listFileName.next());
        File file = new File(pathSaveFile + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.print(pathSaveFile);
        return "";
    }

    @PostMapping("addProduct")
    @ResponseBody
    public void addProduct(@RequestParam String dataJason){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonObject;

        try {
            jsonObject = objectMapper.readTree(dataJason);
            Product product = new Product();

            String nameProduct = jsonObject.get("name-product").asText();
            String price = jsonObject.get("price").asText();
            String gender = jsonObject.get("gender").asText();
            String description = jsonObject.get("description").asText();
            String photo = jsonObject.get("photo").asText();

            ProductCategory category = new ProductCategory();
            category.setIdCategory(jsonObject.get("category").asInt());

            JsonNode jsonListDetail = jsonObject.get("detailProduct");
            Set<DetailProduct> listDetailProduct = new HashSet<>();
            for (JsonNode jasonDetail:jsonListDetail) {
                DetailProduct detailProduct = new DetailProduct();

                ColorProduct colorProduct = new ColorProduct();
                colorProduct.setIdColor(jasonDetail.get("colorProduct").asInt());

                Size size = new Size();
                size.setIdSize(jasonDetail.get("sizeProduct").asInt());

                detailProduct.setColorProduct(colorProduct);
                detailProduct.setSize(size);
                detailProduct.setQuantity(jasonDetail.get("quantity").asInt());

                listDetailProduct.add(detailProduct);

            }
            product.setNameProduct(nameProduct);
            product.setPrice(price);
            product.setGender(gender);
            product.setProductCategory(category);
            product.setDescription(description);
            product.setPhoto(photo);
            product.setListDetailProduct(listDetailProduct);

            System.out.println(product.getProductCategory().getIdCategory());

            productService.addProduct(product);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PostMapping( path = "getListProductById", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JasonProduct getListProductById(@RequestParam int idProduct){
        JasonProduct jasonProduct = new JasonProduct();
        Product product = productService.getProductById(idProduct);

        jasonProduct.setIdProduct(product.getIdProduct());
        jasonProduct.setNameProduct(product.getNameProduct());
        jasonProduct.setPrice(product.getPrice());
        jasonProduct.setGender(product.getGender());
        jasonProduct.setPhoto(product.getPhoto());
        jasonProduct.setDescription(product.getDescription());

        /*jasonProduct.setProductCategory(product.getProductCategory());*/
        ProductCategory productCategory = new ProductCategory();
        productCategory.setIdCategory(product.getProductCategory().getIdCategory());
        productCategory.setNameCategory(product.getProductCategory().getNameCategory());
        jasonProduct.setProductCategory(productCategory);


        /*jasonProduct.setListDetailProduct(product.getListDetailProduct());*/
        Set<DetailProduct> listDetailProduct = new HashSet<>();
        for (DetailProduct value:product.getListDetailProduct()){
            DetailProduct detailProduct = new DetailProduct();
            detailProduct.setIdDetailProduct(value.getIdDetailProduct());

            ColorProduct colorProduct = new ColorProduct();
            colorProduct.setIdColor(value.getColorProduct().getIdColor());
            colorProduct.setNameColor(value.getColorProduct().getNameColor());
            detailProduct.setColorProduct(colorProduct);

            Size size = new Size();
            size.setIdSize(value.getSize().getIdSize());
            detailProduct.setSize(size);

            detailProduct.setQuantity(value.getQuantity());

            listDetailProduct.add(detailProduct);
        }
        jasonProduct.setListDetailProduct(listDetailProduct);

        return jasonProduct;
    }

    @PostMapping("updateProduct")
    @ResponseBody
    public void updateProduct(@RequestParam String dataJason){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonObject;

        try {
            jsonObject = objectMapper.readTree(dataJason);
            Product product = new Product();

            String nameProduct = jsonObject.get("name-product").asText();
            String price = jsonObject.get("price").asText();
            String gender = jsonObject.get("gender").asText();
            String description = jsonObject.get("description").asText();
            String photo = jsonObject.get("photo").asText();
            int idProduct = jsonObject.get("idProduct").asInt();

            ProductCategory category = new ProductCategory();
            category.setIdCategory(jsonObject.get("category").asInt());

            JsonNode jsonListDetail = jsonObject.get("detailProduct");
            Set<DetailProduct> listDetailProduct = new HashSet<>();
            for (JsonNode jasonDetail:jsonListDetail) {
                DetailProduct detailProduct = new DetailProduct();

                ColorProduct colorProduct = new ColorProduct();
                colorProduct.setIdColor(jasonDetail.get("colorProduct").asInt());

                Size size = new Size();
                size.setIdSize(jasonDetail.get("sizeProduct").asInt());

                detailProduct.setColorProduct(colorProduct);
                detailProduct.setSize(size);
                detailProduct.setQuantity(jasonDetail.get("quantity").asInt());

                listDetailProduct.add(detailProduct);

            }
            product.setIdProduct(idProduct);
            product.setNameProduct(nameProduct);
            product.setPrice(price);
            product.setGender(gender);
            product.setProductCategory(category);
            product.setDescription(description);
            product.setPhoto(photo);
            product.setListDetailProduct(listDetailProduct);
            productService.updateProduct(product);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
