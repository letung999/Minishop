package com.letung.dao;


import com.letung.daoImpl.EmployeeImpl;
import com.letung.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeDAO implements EmployeeImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean checkLogin(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> employees = (List<Employee>) session.createQuery("from employee where email ='" + email + "' and passWord ='" + password + "'").getResultList();
        return employees.size() > 0;
    }

    @Override
    @Transactional
    public boolean checkUserExit(String email) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Employee employee = (Employee) session.createQuery("from employee where email ='" + email + "'").getSingleResult();
            return employee.getEmail().equals(email);
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    @Transactional
    public boolean addEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        int idEmployee = (int) session.save(employee);
        return idEmployee > 0;
    }


}
