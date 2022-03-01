package com.letung.controller;


import com.letung.entity.Employee;
import com.letung.service.EmployeeService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("login/")
@SessionAttributes("email")
public class LoginController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String Default() {
        return "login";
    }

    @PostMapping
    @Transactional
    public String SignUp(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword,
                         ModelMap modelMap) {
        boolean exit = employeeService.checkUserExit(email);
        if(!exit){
            boolean checkFormatEmail = validate(email);
            if(checkFormatEmail){
                if(password.equals(confirmPassword)){
                    Employee employee = new Employee();
                    employee.setEmail(email);
                    employee.setPassWord(password);
                    employeeService.addEmployee(employee);
                }
                else{
                    modelMap.addAttribute("signupResult", "Mật Khẩu Không Chính Xác");

                }
            }
            else{
                modelMap.addAttribute("signupResult", "Email Không Hợp Lệ");
            }
        }
        else{
            modelMap.addAttribute("signupResult", "Người Dùng Đã Tồn Tại");
        }
        return "login";
    }


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
