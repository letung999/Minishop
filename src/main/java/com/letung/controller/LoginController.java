package com.letung.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login/")
public class LoginController {
    @GetMapping
    public String Default() {
        return "login";
    }

    @PostMapping
    public String Login(@RequestParam String user, @RequestParam String password){
        if(user.equals("Le Quang Tung") && password.equals("123")){
            return "redirect:/";
        }
        else{
            return "login";
        }
    }
}
