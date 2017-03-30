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
 * Created by catal on 3/26/2017.
 */
@Controller
public class EmployeeController{

    private int localId;
    private String localAccNumber;

    private double localAmount;

    private String localCNP;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/viewUsers.htm", method = RequestMethod.GET)
    public ModelAndView viewUsers(){

        ModelAndView modelAndView = new ModelAndView("viewUsers");

        List<Client> clientList = employeeService.getDefaultClientDAO().getAllClients();

        modelAndView.addObject("clientList",clientList);

        return modelAndView;
    }

    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String logout() {
        return "login";
        }

    @RequestMapping(value = "/addNewUser.htm", method = RequestMethod.GET)
    public String addNewUser() {
        return "addNewUser";
    }

    @RequestMapping(value = "addNewUser.htm", method = RequestMethod.POST)
    public ModelAndView newUserAdded(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "icNumber") String icNumber,
                                     @RequestParam(value = "cnp") String cnp,
                                     @RequestParam(value = "address") String address,
                                     @RequestParam(value = "age") String age,
                                     @RequestParam(value = "accNumber") String accNumber,
                                     @RequestParam(value = "type") String type,
                                     @RequestParam(value = "amount") String amount) throws ParseException{

        ModelAndView modelAndView = new ModelAndView("addNewUser");

        if(!employeeService.testNewClientConsistency(icNumber, cnp)) {
            modelAndView.addObject("message", "IC number or CNP not valid!");
            System.out.println("1");
            return modelAndView;

        }

        if(!employeeService.testNewClientAge(age)){
            modelAndView.addObject("message", "Age cannot contain letters or special characters!");
            System.out.println("4");
            return modelAndView;
        }

        double doubleAmount;

        try{
            doubleAmount = Double.parseDouble(amount);
        }catch (NumberFormatException e){
            modelAndView.addObject("message", "Invalid amount!");
            System.out.println("2");
            return modelAndView;
        }

        if(!employeeService.testAccountConsistency(type)) {
            modelAndView.addObject("message", "Account type or amount not valid!");
            System.out.println("3");
            return modelAndView;
        }

        if(!employeeService.testNegativeAmount(Double.parseDouble(amount))){
            modelAndView.addObject("message", "Amount cannot be negative!");
            return modelAndView;
        }

        int clientId = employeeService.getClientId();
        Client client = new Client(clientId,name, Integer.parseInt(icNumber), cnp, Integer.parseInt(age), address);
        employeeService.getDefaultClientDAO().addClient(client);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        int accountId = employeeService.getAccountId();
        Account account = null;

        account = new Account(accountId, accNumber, type, doubleAmount, df.parse(df.format(date)), clientId);

        employeeService.getDefaultAccountDAO().addAccount(account);

        return modelAndView;
    }

    @RequestMapping(value = "/updateUser.htm", method = RequestMethod.GET)
    public ModelAndView updateUser(){
        ModelAndView modelAndView = new ModelAndView("updateUser");

        List<Client> clientList = employeeService.getDefaultClientDAO().getAllClients();

        modelAndView.addObject("clientList",clientList);

        return modelAndView;
    }

    @RequestMapping(value = "/updateForm.htm", method = RequestMethod.GET)
    public ModelAndView goToUpdateForm(@RequestParam(value = "cnp") String cnp) {

        ModelAndView modelAndView = new ModelAndView("updateForm");

        Client client = employeeService.getDefaultClientDAO().getClient(cnp);

        localCNP = cnp;

        modelAndView.addObject(client);

        return  modelAndView;


    }

    @RequestMapping(value = "/updateUser.htm", method = RequestMethod.POST)
    public ModelAndView updated(@RequestParam(value = "name") String name,
                                @RequestParam(value = "icNumber") String icNumber,
                                @RequestParam(value = "cnp") String cnp,
                                @RequestParam(value = "address") String address,
                                @RequestParam(value = "age") String age) {
        ModelAndView modelAndView = new ModelAndView("updateUser");

        if(!employeeService.testNewClientConsistency(icNumber, cnp)) {
            modelAndView = new ModelAndView("updateForm");
            modelAndView.addObject("message", "IC number or CNP not valid!");
            Client client = employeeService.getDefaultClientDAO().getClient(localCNP);
            modelAndView.addObject(client);
            return modelAndView;
        }

        if(!employeeService.testNewClientAge(age)){
            modelAndView = new ModelAndView("updateForm");
            modelAndView.addObject("message", "Age cannot contain letters or special characters!");
            Client client = employeeService.getDefaultClientDAO().getClient(localCNP);
            modelAndView.addObject(client);
            return modelAndView;
        }

        Client client = employeeService.getDefaultClientDAO().getClient(cnp);
        client.setName(name);
        client.setIcNumber(Integer.parseInt(icNumber));
        client.setCnp(cnp);
        client.setAddress(address);
        client.setAge(Integer.parseInt(age));

        employeeService.getDefaultClientDAO().updateClient(client);

        List<Client> clientList = employeeService.getDefaultClientDAO().getAllClients();

        modelAndView.addObject("clientList",clientList);

        return modelAndView;
    }






}
