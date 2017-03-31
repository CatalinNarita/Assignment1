package com.uni.dao.mappers;

import com.uni.model.Bill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by catal on 3/31/2017.
 */
public class BillRowMapper implements RowMapper<Bill> {
    public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bill bill = new Bill();

        bill.setBillId(rs.getInt(1));
        bill.setCompany(rs.getString(2));
        bill.setAmount(rs.getDouble(3));
        bill.setClientId(rs.getInt(4));

        return bill;
    }
}
