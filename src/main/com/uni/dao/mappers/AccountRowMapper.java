package com.uni.dao.mappers;

import com.uni.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by catal on 3/28/2017.
 */
public class AccountRowMapper implements RowMapper<Account> {

    public Account mapRow(ResultSet rs, int rowNum) throws SQLException{

        Account account = new Account();

        account.setAccountId(rs.getInt(1));
        account.setAccNumber(rs.getString(2));
        account.setType(rs.getString(3));
        account.setAmount(rs.getDouble(4));
        account.setCreationDate(rs.getDate(5));
        account.setClientId(rs.getInt(6));

        return account;

    }

}
