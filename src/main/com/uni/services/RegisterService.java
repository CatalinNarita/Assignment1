package com.uni.services;

import com.uni.dao.implementation.DefaultEmployeeDAO;
import com.uni.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by catal on 3/29/2017.
 */
@Service
public class RegisterService {

    @Autowired
    private DefaultEmployeeDAO defaultEmployeeDAO;

    public DefaultEmployeeDAO getDefaultEmployeeDAO() {
        return this.defaultEmployeeDAO;
    }

    public boolean usernameExists(String username) {
        return defaultEmployeeDAO.usernameExists(username);
    }

    public int getEmployeeId(){
        List<Employee> employees = defaultEmployeeDAO.getAllEmployees();
        int min = 1;
        if(employees.size()>0) {
            int max = employees.get(employees.size() - 1).getEmployeeId();
            for (Employee t : employees) {
                if (min != t.getEmployeeId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }

}
