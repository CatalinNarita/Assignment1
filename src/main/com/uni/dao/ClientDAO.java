package com.uni.dao;

import com.uni.model.Client;
import com.uni.model.Employee;

/**
 * Created by catal on 3/28/2017.
 */
public interface ClientDAO {

    boolean isValidClient(String cnp);

    Client getClient(String cnp);

    void addClient(Client client);

    void deleteClient(Client client);

    void updateClient(Client client);

}
