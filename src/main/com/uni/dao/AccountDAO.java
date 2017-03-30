package com.uni.dao;

import com.uni.model.Account;
import com.uni.model.Client;

import java.util.List;

/**
 * Created by catal on 3/28/2017.
 */
public interface AccountDAO {

    List<Account> getAccount(int clientId);

    void addAccount(Account account);

    void deleteAccount(Account account);

    void updateAccount(String accNumber, double amount, String type, int accountId);

}
