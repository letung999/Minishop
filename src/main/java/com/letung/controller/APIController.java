package com.letung.controller;


import com.letung.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api/")
@SessionAttributes("email")
public class APIController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("checkLogin")
    @ResponseBody
    public String checkLogin(@RequestParam String email, @RequestParam String password, ModelMap modelMap){
        boolean check = employeeService.checkLogin(email, password);
        modelMap.addAttribute("email", email);
        return ""+ check;
    }
}
