package com.uni.controller;

import com.uni.model.Account;
import com.uni.model.Employee;
import com.uni.services.AdministratorService;
import com.uni.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by catal on 3/30/2017.
 */
@Controller
public class AdministratorEmployeeController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private RegisterService registerService;

    private int localId;

    @RequestMapping(value = "/viewEmployees.htm", method = RequestMethod.GET)
    public ModelAndView viewEmployees(){
        ModelAndView modelAndView = new ModelAndView("viewEmployees");
        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/addEmployee.htm", method = RequestMethod.GET)
    public ModelAndView addEmployeeFOrm(){
        ModelAndView modelAndView = new ModelAndView("addEmployee");
        return modelAndView;
    }

    @RequestMapping(value = "/addEmployee.htm", method = RequestMethod.POST)
    public ModelAndView addEmployee(@RequestParam(value = "name") String name,
                                    @RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password){
        ModelAndView modelAndView = new ModelAndView("viewEmployees");

        if(administratorService.usernameExists(username)){
            modelAndView = new ModelAndView("addEmployee");
            modelAndView.addObject("message", "Username already exists!");
            return modelAndView;
        }
        int employeeId = registerService.getEmployeeId();
        Employee employee = new Employee(employeeId, name, username, password, (byte)0);
        administratorService.getDefaultEmployeeDAO().addEmployee(employee);
        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/updateEmployee.htm", method = RequestMethod.GET)
    public ModelAndView updateEmployee(){
        ModelAndView modelAndView = new ModelAndView("updateEmployee");
        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/updateEmployeeForm.htm", method = RequestMethod.GET)
    public ModelAndView updateEmployeeForm(@RequestParam(value = "username") String username,
                                           @RequestParam(value = "password") String password,
                                           @RequestParam(value = "employeeId") int employeeId){
        ModelAndView modelAndView = new ModelAndView("updateEmployeeForm");
        Employee employee = administratorService.getDefaultEmployeeDAO().getEmployee(username,password);
        modelAndView.addObject("employee",employee);
        localId = employeeId;
        return modelAndView;
    }

    @RequestMapping(value = "/updateEmployee.htm", method = RequestMethod.POST)
    public ModelAndView updated(@RequestParam(value = "name") String name,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password){
        ModelAndView modelAndView = new ModelAndView("updateEmployee");

        Employee employee = administratorService.getDefaultEmployeeDAO().getEmployeeById(localId);

        employee.setName(name);
        employee.setUsername(username);
        employee.setPassword(password);

        administratorService.getDefaultEmployeeDAO().updateEmployee(employee);

        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteEmployee.htm", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(){
        ModelAndView modelAndView = new ModelAndView("deleteEmployee");
        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteEmployee.htm", method = RequestMethod.POST)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response){
        String[] ids = request.getParameterValues("checked");

        if(ids == null) {
            ModelAndView modelAndView = new ModelAndView("deleteEmployee");
            List<Employee> employees = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
            modelAndView.addObject("employeeList",employees);
            modelAndView.addObject("message","There are no more employees remaining!");
            return modelAndView;
        }

        administratorService.deleteReportsBeforeEmployees(ids);
        administratorService.deleteSelectedEmployees(ids);
        ModelAndView modelAndView = new ModelAndView("deleteEmployee");
        List<Employee> employees = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employees);
        return modelAndView;
    }
}
