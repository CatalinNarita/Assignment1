package com.uni.controller;

import com.uni.model.Account;
import com.uni.model.Client;
import com.uni.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by catal on 3/30/2017.
 */
@Controller
public class EmployeeAccountController {

    private int localId;

    private int localAccId;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/accountList.htm", method = RequestMethod.GET)
    public ModelAndView usersAccounts(){
        ModelAndView modelAndView = new ModelAndView("accountList");

        List<Client> clientList = employeeService.getDefaultClientDAO().getAllClients();

        modelAndView.addObject("clientList",clientList);

        return modelAndView;
    }

    @RequestMapping(value = "/account.htm", method = RequestMethod.GET)
    public ModelAndView account(@RequestParam(value = "clientId") int clientId) {
        ModelAndView modelAndView = new ModelAndView("account");

        List<Account> accounts = employeeService.getDefaultAccountDAO().getAccount(clientId);

        modelAndView.addObject("accountList",accounts);
        localId = clientId;

        return modelAndView;

    }

    @RequestMapping(value = "/editAccount.htm", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(value = "accountId") int accountId){
        ModelAndView modelAndView = new ModelAndView("editAccount");

        Account account = employeeService.getDefaultAccountDAO().getAccountById(accountId);

        localAccId = accountId;

        modelAndView.addObject(account);

        return  modelAndView;
    }
    @RequestMapping(value = "/accountEdited.htm", method = RequestMethod.GET)
    public ModelAndView accountEdited(@RequestParam(value = "accountId") int accountId,
                                      @RequestParam(value = "accNumber") String accNumber,
                                      @RequestParam(value = "type") String type,
                                      @RequestParam(value = "amount") String amount) throws ParseException {

        ModelAndView modelAndView;

        double doubleAmount;

        try{
            doubleAmount = Double.parseDouble(amount);
        }catch (NumberFormatException e){
            modelAndView = new ModelAndView("editAccount");
            modelAndView.addObject("message", "Invalid amount!");
            Account account = employeeService.getDefaultAccountDAO().getAccountById(localAccId);
            modelAndView.addObject(account);
            return modelAndView;
        }

        if(!employeeService.testAccountConsistency(type)) {
            modelAndView = new ModelAndView("editAccount");
            modelAndView.addObject("message", "Account type or amount not valid!");
            Account account = employeeService.getDefaultAccountDAO().getAccountById(localAccId);
            modelAndView.addObject(account);
            return modelAndView;
        }

        if(!employeeService.testNegativeAmount(Double.parseDouble(amount))){
            modelAndView = new ModelAndView("editAccount");
            modelAndView.addObject("message", "Amount cannot be negative!");
            Account account = employeeService.getDefaultAccountDAO().getAccountById(localAccId);
            modelAndView.addObject(account);
            return modelAndView;
        }

        employeeService.getDefaultAccountDAO().updateAccount(accNumber,doubleAmount,type,accountId);

        modelAndView = new ModelAndView("account");

        List<Account> accounts = employeeService.getDefaultAccountDAO().getAccountListById(accountId);

        modelAndView.addObject("accountList",accounts);

        return modelAndView;

    }

    @RequestMapping(value = "/newAccount.htm", method = RequestMethod.GET)
    public String newAccount() {
        return "newAccount";
    }

    @RequestMapping(value = "/addAccount.htm", method = RequestMethod.GET)
    public ModelAndView newAccountAdded(@RequestParam(value = "accNumber") String accNumber,
                                        @RequestParam(value = "type") String type,
                                        @RequestParam(value = "amount") String amount) throws ParseException{
        ModelAndView modelAndView;

        System.out.println("AICI");

        int clientId = localId;

        double doubleAmount;

        try{
            doubleAmount = Double.parseDouble(amount);
        }catch (NumberFormatException e){
            modelAndView = new ModelAndView("newAccount");
            modelAndView.addObject("message", "Invalid amount!");
            System.out.println("2");
            return modelAndView;
        }

        if(!employeeService.testAccountConsistency(type)) {
            modelAndView = new ModelAndView("newAccount");
            modelAndView.addObject("message", "Account type or amount not valid!");
            System.out.println("3");
            return modelAndView;
        }

        if(!employeeService.testNegativeAmount(Double.parseDouble(amount))){
            modelAndView = new ModelAndView("newAccount");
            modelAndView.addObject("message", "Amount cannot be negative!");
            return modelAndView;
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        int accountId1 = employeeService.getAccountId();
        Account account = null;

        System.out.println(accountId1);

        account = new Account(accountId1, accNumber, type, doubleAmount, df.parse(df.format(date)), clientId);

        employeeService.getDefaultAccountDAO().addAccount(account);

        modelAndView = new ModelAndView("account");

        List<Account> accounts = employeeService.getDefaultAccountDAO().getAccount(clientId);

        modelAndView.addObject(accounts);

        return modelAndView;


    }

    @RequestMapping(value = "/account.htm", method = RequestMethod.POST)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response){
        String[] ids = request.getParameterValues("checked");

        if(ids == null) {
            ModelAndView modelAndView = new ModelAndView("account");
            List<Account> accounts = employeeService.getDefaultAccountDAO().getAccount(localId);
            modelAndView.addObject("accountList",accounts);
            modelAndView.addObject("message","There are no more accounts remaining!");
            return modelAndView;
        }

        employeeService.deleteSelectedAccounts(ids);
        ModelAndView modelAndView = new ModelAndView("account");
        List<Account> accounts = employeeService.getDefaultAccountDAO().getAccount(localId);
        modelAndView.addObject("accountList",accounts);
        return modelAndView;
    }

}
