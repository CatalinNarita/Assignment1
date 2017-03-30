package com.uni.services;

import com.uni.dao.implementation.DefaultEmployeeDAO;
import com.uni.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by catal on 3/26/2017.
 */
@Service
public class LoginService {

    @Autowired
    private DefaultEmployeeDAO defaultEmployeeDAO;

    public void setDefaultUserDao(DefaultEmployeeDAO defaultEmployeeDAO) {

        this.defaultEmployeeDAO = defaultEmployeeDAO;
    }

    public boolean loginEmployee(String username, String password) {
        if (defaultEmployeeDAO.isValidEmployee(username, password)) {
            return true;
        }
        return false;
    }

    public Employee getEmployee(String username, String password) {
        return defaultEmployeeDAO.getEmployee(username, password);
    }

}
