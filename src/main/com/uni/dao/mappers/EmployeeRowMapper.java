package com.uni.dao.mappers;

import com.uni.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by catal on 3/26/2017.
 */
public class EmployeeRowMapper implements RowMapper<Employee>{

    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{

        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt(1));
        employee.setUsername(rs.getString(2));
        employee.setPassword(rs.getString(3));
        employee.setName(rs.getString(4));
        employee.setRole(rs.getByte(5));

        return employee;

    }

}
