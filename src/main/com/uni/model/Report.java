package com.uni.model;

import java.util.Date;

/**
 * Created by catal on 3/26/2017.
 */
public class Report {

    private int reportId;
    private int employeeId;
    private int adminId;
    private Date startDate;
    private Date endDate;
    private String content;

    public Report(){}

    public Report(int reportId, int employeeId, int adminId, Date startDate, Date endDate, String details) {
        this.reportId = reportId;
        this.employeeId = employeeId;
        this.adminId = adminId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = details;
    }

    public Report(Date startDate, Date endDate, String content) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
