package com.uni.controller;

import com.uni.model.Employee;
import com.uni.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by catal on 3/29/2017.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register.htm", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register.htm", method = RequestMethod.POST)
    public ModelAndView registered(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "username") String username,
                                   @RequestParam(value = "password1") String password1,
                                   @RequestParam(value = "password2") String password2) {

        ModelAndView modelAndView;

        //test if username exists
        if(registerService.usernameExists(username)) {
            modelAndView = new ModelAndView("register");
            modelAndView.addObject("message","Username already exists!");
            return modelAndView;
        }

        //test if passwords match
        if(!password1.equals(password2)){
            modelAndView = new ModelAndView("register");
            modelAndView.addObject("message","Password do no match!");
            return modelAndView;
        }

        int employeeId = registerService.getEmployeeId();
        Employee employee = new Employee(employeeId,username, password1, name, (byte)(0));

        registerService.getDefaultEmployeeDAO().addEmployee(employee);

        modelAndView = new ModelAndView("login");

        modelAndView.addObject("message","You have successfully registered! You can login in with your new account.");
        return modelAndView;
    }

}
