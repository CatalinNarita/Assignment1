package com.uni.services;

import com.uni.dao.implementation.DefaultAccountDAO;
import com.uni.dao.implementation.DefaultBillDAO;
import com.uni.dao.implementation.DefaultClientDAO;
import com.uni.dao.implementation.DefaultEmployeeDAO;
import com.uni.model.Account;
import com.uni.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by catal on 3/29/2017.
 */
@Service
public class EmployeeService {

    @Autowired
    private DefaultClientDAO defaultClientDAO;

    @Autowired
    private DefaultAccountDAO defaultAccountDAO;

    @Autowired
    private DefaultBillDAO defaultBillDAO;

    public DefaultClientDAO getDefaultClientDAO() {
        return defaultClientDAO;
    }

    public DefaultAccountDAO getDefaultAccountDAO() {
        return defaultAccountDAO;
    }

    public DefaultBillDAO getDefaultBillDAO(){ return defaultBillDAO;};

    public void setDefaultClientDAO(DefaultClientDAO defaultClientDAO) {
        this.defaultClientDAO = defaultClientDAO;
    }

    public List<Client> getAllClients() {
        return defaultClientDAO.getAllClients();
    }

    public boolean testNewClientConsistency(String icNumber, String cnp)  {
        if(cnp.equals("") || icNumber.equals(""))
            return false;
        if(!(cnp.chars().allMatch(c -> c >= 48 && c <= 57)))
            return false;
        if (("12".indexOf(cnp.charAt(0))) < 0)
            return false;
        if(!(icNumber.chars().allMatch(c -> c >= 48 && c <= 57)))
            return false;
        return true;
    }

    public boolean testAccountConsistency(String type){
        if(!(type.equals("spending")) && !(type.equals("saving")))
            return false;
        return true;
    }

    public boolean testNewClientAge(String age) {
        if(!(age.chars().allMatch(c -> c >= 48 && c <= 57)))
            return false;
        return true;
    }

    public boolean testNegativeAmount(double amount) {
        if(amount < 0)
            return false;
        return true;
    }

    public int getClientId(){
        List<Client> clients = defaultClientDAO.getAllClients();
        int min = 1;
        if(clients.size()>0) {
            int max = clients.get(clients.size() - 1).getClientId();
            for (Client t : clients) {
                if (min != t.getClientId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }

    public int getAccountId(){
        List<Account> accounts = defaultAccountDAO.getAllAccounts();
        int min = 1;
        if(accounts.size()>0) {
            int max = accounts.get(accounts.size() - 1).getAccountId();
            for (Account t : accounts) {
                if (min != t.getAccountId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }

    public void addNewClient(Client client) {
        defaultClientDAO.addClient(client);
    }

    public void addNewAccount(Account account){
        defaultAccountDAO.addAccount(account);
    }

    public void deleteClient(Client client){
        defaultClientDAO.deleteClient(client);
    }

    public void deleteSelectedAccounts(String[] ids) {
        for(String s: ids) {
            defaultAccountDAO.deleteAccountById(Integer.parseInt(s));
        }
    }

    public boolean accountExists(String accNumber) {
        return defaultAccountDAO.exists(accNumber);
    }

}
