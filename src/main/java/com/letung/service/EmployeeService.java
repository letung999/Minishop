package com.letung.service;


import com.letung.dao.EmployeeDAO;
import com.letung.daoImpl.EmployeeImpl;
import com.letung.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService implements EmployeeImpl {

    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public boolean checkLogin(String email, String password) {
        return employeeDAO.checkLogin(email, password);
    }


    @Override
    public boolean checkUserExit(String email) {
        return employeeDAO.checkUserExit(email);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }
}
