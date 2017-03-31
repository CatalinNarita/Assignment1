package com.uni.controller;

import com.uni.model.Account;
import com.uni.model.Bill;
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
 * Created by catal on 3/31/2017.
 */
@Controller
public class EmployeeBillController {

    @Autowired
    private EmployeeService employeeService;

    private int localId;
    private double localAmount;
    private int localBillId;

    @RequestMapping(value = "/viewBills.htm", method = RequestMethod.GET)
    public ModelAndView viewBills(){

        ModelAndView modelAndView = new ModelAndView("viewBills");

        List<Client> clientList = employeeService.getDefaultClientDAO().getAllClients();

        modelAndView.addObject("clientList",clientList);

        return modelAndView;
    }

    @RequestMapping(value = "/bill.htm", method = RequestMethod.GET)
    public ModelAndView bills(@RequestParam(value = "clientId") int clientId){

        ModelAndView modelAndView = new ModelAndView("bill");

        List<Bill> bills = employeeService.getDefaultBillDAO().getBillsForClient(clientId);

        modelAndView.addObject("billList",bills);

        localId = clientId;

        return modelAndView;
    }

    @RequestMapping(value = "/billForm.htm", method = RequestMethod.GET)
    public ModelAndView billForm(@RequestParam(value = "amount") double amount,
                                 @RequestParam(value = "billId") int billId){

        ModelAndView modelAndView = new ModelAndView("billForm");

        modelAndView.addObject("needed", amount);

        localAmount = amount;
        localBillId = billId;

        return modelAndView;
    }

    @RequestMapping(value = "/bill.htm", method = RequestMethod.POST)
    public ModelAndView billProcessed(@RequestParam(value = "accNumber") String accNumber){

        ModelAndView modelAndView = new ModelAndView("bill");

        if(!employeeService.accountExists(accNumber)){
            modelAndView = new ModelAndView("billForm");
            modelAndView.addObject("message", "Account not existent!");
            modelAndView.addObject("needed",localAmount);
            return modelAndView;
        }

        if(employeeService.getDefaultAccountDAO().getAccountByNumber(accNumber).getAmount() < localAmount){
            modelAndView = new ModelAndView("billForm");
            modelAndView.addObject("message", "The account doesn't have enough funds!");
            modelAndView.addObject("needed",localAmount);
            return modelAndView;
        }

        employeeService.getDefaultAccountDAO().updateSourceAccount(localAmount,accNumber);

        employeeService.getDefaultBillDAO().deleteBill(localBillId);

        List<Bill> bills = employeeService.getDefaultBillDAO().getBillsForClient(localId);

        modelAndView.addObject("billList",bills);

        return modelAndView;
    }
}
