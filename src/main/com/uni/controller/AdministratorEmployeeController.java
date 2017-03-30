package com.uni.controller;

import com.uni.model.Client;
import com.uni.model.Employee;
import com.uni.services.AdministratorService;
import com.uni.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by catal on 3/30/2017.
 */
@Controller
public class AdministratorEmployeeController {

    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/viewEmployees.htm", method = RequestMethod.GET)
    public ModelAndView viewEmployees(){

        ModelAndView modelAndView = new ModelAndView("viewEmployees");

        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();

        modelAndView.addObject("employeeList",employeeList);

        return modelAndView;
    }

}
