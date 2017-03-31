package com.uni.dao.implementation;

import com.uni.dao.AccountDAO;
import com.uni.dao.mappers.AccountRowMapper;
import com.uni.dao.mappers.ClientRowMapper;
import com.uni.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by catal on 3/28/2017.
 */
@Repository
public class DefaultAccountDAO implements AccountDAO{

    private JdbcTemplate jdbcTemplate;

    private Account account;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public boolean exists(String accNumber){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select count(*) from account where accNumber = '" + accNumber + "';";
        int exists = jdbcTemplate.queryForObject(SQL, Integer.class);
        if (exists == 1) {
            return true;
        }
        return false;
    }

    public List<Account> getAllAccounts(){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from account";
        return jdbcTemplate.query(SQL, new AccountRowMapper());
    }

    public List<Account> getAccount(int clientId){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from account where clientId = " + clientId + ";";
        return jdbcTemplate.query(SQL, new AccountRowMapper());
    }

    public Account getAccountById(int accountId){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from account where accountId = " + accountId + ";";
        return jdbcTemplate.queryForObject(SQL, new AccountRowMapper());
    }

    public Account getAccountByNumber(String accNumber){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from account where accNumber = '" + accNumber + "';";
        return jdbcTemplate.queryForObject(SQL, new AccountRowMapper());
    }

    public void addAccount(Account account){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "insert into account values (?,?,?,?,?,?)";
        jdbcTemplate.update(SQL, account.getAccountId(), account.getAccNumber(), account.getType(), account.getAmount(), account.getCreationDate(), account.getClientId());
    }

    public void deleteAccount(Account account){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "delete from account where accountId = ?";
        jdbcTemplate.update(SQL, account.getAccountId());
    }
    public void deleteAccountById(int accountId){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "delete from account where accountId = ?";
        jdbcTemplate.update(SQL, accountId);
    }

    public void updateAccount(String accNumber, double amount, String type, int accountId){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "update account set accNumber = ?, type = ?, amount = ? where accountId = ?";
        jdbcTemplate.update(SQL,accNumber, type, amount, accountId);
    }

    public void updateDestinationAccount(double amount, String accNumber){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL1 = "select amount from account where accNumber = '" + accNumber + "';";
        double oldAmount = jdbcTemplate.queryForObject(SQL1,Double.class);

        String SQL = "update account set amount = ? where accNumber = ?";
        jdbcTemplate.update(SQL,oldAmount + amount, accNumber);
    }

    public void updateSourceAccount(double amount, String accNumber){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL1 = "select amount from account where accNumber = '" + accNumber + "';";
        double oldAmount = jdbcTemplate.queryForObject(SQL1,Double.class);

        String SQL = "update account set amount = ? where accNumber = ?";
        jdbcTemplate.update(SQL,oldAmount - amount, accNumber);
    }

    public List<Account> getAccountListById(int accountId){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL1 = "select clientId from account where accountId = " + accountId + ";";
        int clientId = jdbcTemplate.queryForObject(SQL1, Integer.class);

        String SQL2 = "select * from account where clientId = " + clientId + ";";
        return jdbcTemplate.query(SQL2, new AccountRowMapper());
    }

    public int getClientIdByAccountId(int accountId){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL1 = "select clientId from account where accountId = " + accountId + ";";
        return jdbcTemplate.queryForObject(SQL1, Integer.class);
    }

}
