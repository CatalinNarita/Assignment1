package com.uni.dao.implementation;

import com.uni.dao.EmployeeDAO;
import com.uni.dao.mappers.EmployeeRowMapper;
import com.uni.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by catal on 3/26/2017.
 */
@Repository
public class DefaultEmployeeDAO implements EmployeeDAO {

    private static final String QUERY_1 = "select count(*) from employee where username = '";
    private static final String QUERY_2 = "' and password = '";
    private static final String QUERY_3 = "select * from employee where username = '";
    private static final String QUERY_4 = "' and password = '";
    private JdbcTemplate jdbcTemplate;

    private Employee employee;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public boolean isValidEmployee(String username, String password) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        int exists = jdbcTemplate.queryForObject(QUERY_1 + username + QUERY_2 + password + "';", Integer.class);
        if (exists == 1) {
            return true;
        }
        return false;
    }

    public List<Employee> getAllEmployees(){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from employee";
        return jdbcTemplate.query(SQL, new EmployeeRowMapper());
    }

    public List<Employee> getNormalEmployees(){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from employee where role = 0";
        return jdbcTemplate.query(SQL, new EmployeeRowMapper());
    }

    public Employee getEmployee(String username, String password){
        employee = jdbcTemplate.queryForObject(QUERY_3 + username + QUERY_4 + password + "';", new EmployeeRowMapper());
        return this.employee;
    }

    public void addEmployee(Employee employee) {
        String SQL = "insert into employee values (?,?,?,?,?);";
        jdbcTemplate.update(SQL,employee.getEmployeeId(),employee.getUsername(),employee.getPassword(),employee.getName(), employee.getRole());
    }

    public void deleteEmployee(Employee employee) {
        String SQL = "delete from employee where employeeId = ?;" ;
        jdbcTemplate.update(SQL, employee.getEmployeeId());
    }

    public void updateEmployee(Employee employee) {
        String SQL = "update employee set username = ?, password = ?, name = ? where employeeId = ?;";
        jdbcTemplate.update(SQL, employee.getUsername(), employee.getPassword(),employee.getName());
    }

    public boolean usernameExists(String username) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String SQL = "select count(*) from employee where username = '" + username + "';";
        int exists = jdbcTemplate.queryForObject(SQL,Integer.class);

        if(exists == 1)
            return true;
        return false;
    }

}
