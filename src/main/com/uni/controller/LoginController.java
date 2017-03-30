package com.uni.controller;

import com.uni.model.Employee;
import com.uni.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * Created by catal on 3/26/2017.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String enter() {
        return "login";
    }

    @RequestMapping(value = "/employee.htm", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        Employee employee;
        ModelAndView modelView;
        if (loginService.loginEmployee(username, password)) {
            employee = loginService.getEmployee(username, password);
            //session.setAttribute("name", employee.getFirstName());
            //session.setAttribute("role", user.getRole());
            if(employee.getRole() == 0) {
                modelView = new ModelAndView("employee");
                modelView.addObject("name", employee.getName());
                return modelView;
            }
            else{
                modelView = new ModelAndView("administrator");
                modelView.addObject("name", employee.getName());
                return modelView;
            }
        }
        else
        {
            modelView = new ModelAndView("login");
            modelView.addObject("message", "Invalid username or password!");
            return modelView;
        }
    }


}
