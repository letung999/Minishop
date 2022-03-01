package com.letung.daoImpl;

import com.letung.entity.Employee;

public interface EmployeeImpl {
    boolean checkLogin(String email, String password);
    boolean checkUserExit(String email);
    boolean addEmployee(Employee employee);
}
