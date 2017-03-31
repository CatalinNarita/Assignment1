package com.uni.controller;

import com.uni.model.Employee;
import com.uni.model.Report;
import com.uni.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by catal on 3/31/2017.
 */
@Controller
public class AdministratorReportController {

    @Autowired
    private AdministratorService administratorService;

    private int localEmployeeId;

    @RequestMapping(value = "/generateReport.htm", method = RequestMethod.GET)
    public ModelAndView viewEmployees(){
        ModelAndView modelAndView = new ModelAndView("generateReport");
        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/writeReport.htm", method = RequestMethod.GET)
    public ModelAndView writeReport(@RequestParam(value = "employeeName") String employeeName,
                                    @RequestParam(value = "employeeId") int employeeId){
        ModelAndView modelAndView = new ModelAndView("writeReport");

        localEmployeeId = employeeId;

        modelAndView.addObject("name",employeeName);
        return modelAndView;
    }

    @RequestMapping(value = "/generateReport.htm", method = RequestMethod.POST)
    public ModelAndView reportEmployees(@RequestParam(value = "start") String startDate,
                                        @RequestParam(value = "end") String endDate,
                                        @RequestParam(value = "content") String content,
                                        HttpSession session) throws ParseException{
        ModelAndView modelAndView = new ModelAndView("generateReport");

        if(!administratorService.testDates(startDate, endDate)){
            modelAndView = new ModelAndView("writeReport");
            modelAndView.addObject("message","Invalid start date or end date!");
            return  modelAndView;
        }

        if(administratorService.startDateInTheFuture(startDate)){
            modelAndView = new ModelAndView("writeReport");
            modelAndView.addObject("message","Start date must be in the past!");
            return  modelAndView;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Report report = new Report(df.parse(startDate), df.parse(endDate), content);

        int reportId = administratorService.getReportId();

        report.setReportId(reportId);
        report.setEmployeeId(localEmployeeId);
        report.setAdminId((int)session.getAttribute("adminId"));

        administratorService.getDefaultReportDAO().addReport(report);

        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/viewReport.htm", method = RequestMethod.GET)
    public ModelAndView viewReports(){
        ModelAndView modelAndView = new ModelAndView("viewReport");
        List<Employee> employeeList = administratorService.getDefaultEmployeeDAO().getNormalEmployees();
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/report.htm", method = RequestMethod.GET)
    public ModelAndView report(@RequestParam(value = "empoyeeName") String name,
                               @RequestParam(value = "employeeId") int employeeId){
        ModelAndView modelAndView = new ModelAndView("report");

        List<Report> reports = administratorService.getDefaultReportDAO().getReportsByEmployeeId(employeeId);

        modelAndView.addObject("reportList",reports);

        modelAndView.addObject("emp",name);


        return modelAndView;
    }

}
