package com.uni.services;

import com.uni.dao.implementation.DefaultEmployeeDAO;
import com.uni.dao.implementation.DefaultReportDAO;
import com.uni.model.Account;
import com.uni.model.Employee;
import com.uni.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by catal on 3/30/2017.
 */
@Service
public class AdministratorService {

    @Autowired
    private DefaultEmployeeDAO defaultEmployeeDAO;

    @Autowired
    private DefaultReportDAO defaultReportDAO;

    public DefaultEmployeeDAO getDefaultEmployeeDAO() {
        return defaultEmployeeDAO;
    }

    public DefaultReportDAO getDefaultReportDAO() {
        return defaultReportDAO;
    }

    public boolean usernameExists(String username) {
        return defaultEmployeeDAO.usernameExists(username);
    }

    public void deleteSelectedEmployees(String[] ids) {
        for(String s: ids) {
            defaultEmployeeDAO.deleteEmployeeById(Integer.parseInt(s));
        }
    }

    public void deleteReportsBeforeEmployees(String[] ids) {
        Arrays.stream(ids).forEach(System.out::println);
        for(String s: ids) {
            defaultReportDAO.deleteReportByEmployeeId(Integer.parseInt(s));
        }
    }

    public boolean testDates(String startDate, String endDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date newStartDate;
        Date newEndDate;
        try{
            newStartDate = df.parse(startDate);
            newEndDate = df.parse(endDate);
            if(newStartDate.compareTo(newEndDate) > 0)
                return false;
            return true;
        }catch(ParseException e){
            return false;
        }
    }

    public boolean startDateInTheFuture(String startDate) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date = df.parse(df.format(date));

        Date newStartDate = df.parse(startDate);

        if(newStartDate.compareTo(date) > 0 )
            return true;
        return false;
    }

    public int getReportId(){
        List<Report> reports = defaultReportDAO.getAllReports();
        int min = 1;
        if(reports.size()>0) {
            int max = reports.get(reports.size() - 1).getReportId();
            for (Report t : reports) {
                if (min != t.getReportId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }
}
