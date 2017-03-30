package com.uni.dao;
import com.uni.model.Employee;

/**
 * Created by catal on 3/26/2017.
 */
public interface EmployeeDAO {

    boolean isValidEmployee(String username, String password);

    Employee getEmployee(String username, String password);

    void addEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    void updateEmployee(Employee employee);

}
