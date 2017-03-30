package com.uni.dao.mappers;

import com.uni.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by catal on 3/28/2017.
 */
public class ClientRowMapper implements RowMapper<Client>{

    public Client mapRow(ResultSet rs, int rowNum) throws SQLException{

        Client client = new Client();

        client.setClientId(rs.getInt(1));
        client.setName(rs.getString(2));
        client.setIcNumber(rs.getInt(3));
        client.setCnp(rs.getString(4));
        client.setAddress(rs.getString(5));
        client.setAge(rs.getInt(6));

        return client;
    }

}
