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

import java.util.List;

/**
 * Created by catal on 3/30/2017.
 */
@Controller
public class EmployeeAcountTransferController {

    private int localId;
    private String localAccNumber;

    private double localAmount;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/transferMoneyClientList.htm", method = RequestMethod.GET)
    public ModelAndView transferClient() {
        ModelAndView modelAndView = new ModelAndView("transferMoneyClientList");

        List<Client> clientList = employeeService.getDefaultClientDAO().getAllClients();

        modelAndView.addObject("clientList",clientList);

        //localId = clientId;

        return modelAndView;
    }

    @RequestMapping(value = "/transferMoneyAccountList.htm", method = RequestMethod.GET)
    public ModelAndView transferAccount(@RequestParam(value = "clientId") int clientId) {
        ModelAndView modelAndView = new ModelAndView("transferMoneyAccountList");

        List<Account> accounts = employeeService.getDefaultAccountDAO().getAccount(clientId);

        modelAndView.addObject("accountList",accounts);

        localId = clientId;

        return modelAndView;
    }

    @RequestMapping(value = "/transferForm.htm", method = RequestMethod.GET)
    public ModelAndView transferForm(@RequestParam(value = "amount") double amount,
                                     @RequestParam(value = "accNumber") String accNumber) {
        ModelAndView modelAndView = new ModelAndView("transferForm");

        localAccNumber = accNumber;

        localAmount = amount;

        modelAndView.addObject("message","Suma disponibila in cont:");
        modelAndView.addObject("amount",amount);

        return modelAndView;
    }

    @RequestMapping(value = "transferMoneyAccountList.htm", method = RequestMethod.POST)
    public ModelAndView transferred(@RequestParam(value = "amount") String amount,
                                    @RequestParam(value = "accNumber") String accNumber) {

        ModelAndView modelAndView = new ModelAndView("transferMoneyAccountList");

        double doubleAmount;

        try{
            doubleAmount = Double.parseDouble(amount);
        }catch (NumberFormatException e){
            modelAndView = new ModelAndView("transferForm");
            modelAndView.addObject("message1", "Invalid amount!");
            modelAndView.addObject("message","Suma disponibila in cont:");
            modelAndView.addObject("amount",localAmount);
            return modelAndView;
        }

        if(!employeeService.testNegativeAmount(doubleAmount)) {
            modelAndView = new ModelAndView("transferForm");
            modelAndView.addObject("message1", "Account type or amount not valid!");
            modelAndView.addObject("message","Suma disponibila in cont:");
            modelAndView.addObject("amount",localAmount);
            return modelAndView;
        }

        if(doubleAmount > localAmount){
            modelAndView = new ModelAndView("transferForm");
            modelAndView.addObject("message1", "You don't have enough funds!");
            modelAndView.addObject("message","Suma disponibila in cont:");
            modelAndView.addObject("amount",localAmount);
            return modelAndView;
        }

        if(!employeeService.accountExists(accNumber)){
            modelAndView = new ModelAndView("transferForm");
            modelAndView.addObject("message1", "Destination account not existent!");
            modelAndView.addObject("message","Suma disponibila in cont:");
            modelAndView.addObject("amount",localAmount);
            return modelAndView;
        }

        employeeService.getDefaultAccountDAO().updateSourceAccount(doubleAmount, localAccNumber);

        employeeService.getDefaultAccountDAO().updateDestinationAccount(doubleAmount, accNumber);

        List<Account> accounts = employeeService.getDefaultAccountDAO().getAccount(localId);

        modelAndView.addObject("accountList",accounts);

        return modelAndView;

    }

}
