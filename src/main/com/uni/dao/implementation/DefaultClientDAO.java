package com.uni.dao.implementation;

import com.uni.dao.ClientDAO;
import com.uni.dao.mappers.ClientRowMapper;
import com.uni.model.Client;
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
public class DefaultClientDAO implements ClientDAO{

    private JdbcTemplate jdbcTemplate;

    private Client client;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public boolean isValidClient(String cnp){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select count(*) from client where cnp = ?;";
        int exists = jdbcTemplate.queryForObject(SQL, Integer.class);
        if (exists == 1) {
            return true;
        }
        return false;
    }

    public List<Client> getAllClients(){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from client";
        return jdbcTemplate.query(SQL, new ClientRowMapper());
    }

    public Client getClient(String cnp){
        String SQL = "select * from client where cnp = '" + cnp + "';";
        return jdbcTemplate.queryForObject(SQL,new ClientRowMapper());
    }

    public void addClient(Client client){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "insert into client  values (?,?,?,?,?,?)";
        jdbcTemplate.update(SQL,client.getClientId(),client.getName(),client.getIcNumber(),client.getCnp(),client.getAddress(),client.getAge());
    }

    public void deleteClient(Client client){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "delete from client where clientId = ?;";
        jdbcTemplate.update(SQL, client.getClientId());
    }

    public void updateClient(Client client){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "update client set name = ?, address = ?, age = ? where clientId = ?;";
        jdbcTemplate.update(SQL, client.getName(),client.getAddress(), client.getAge(), client.getClientId());
    }



}
