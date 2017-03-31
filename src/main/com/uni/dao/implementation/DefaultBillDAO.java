package com.uni.dao.implementation;

import com.uni.dao.mappers.BillRowMapper;
import com.uni.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by catal on 3/31/2017.
 */
@Repository
public class DefaultBillDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public List<Bill> getAllBills() {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from bill;";
        return jdbcTemplate.query(SQL, new BillRowMapper());
    }

    public void deleteBill(int billId) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "delete from bill where billId = ?";
        jdbcTemplate.update(SQL, billId);
    }

    public List<Bill> getBillsForClient(int clientId) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from bill where clientId = " + clientId +";";
        return jdbcTemplate.query(SQL, new BillRowMapper());
    }

}
