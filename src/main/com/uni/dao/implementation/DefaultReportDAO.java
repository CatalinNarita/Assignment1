package com.uni.dao.implementation;

import com.uni.dao.mappers.ClientRowMapper;
import com.uni.dao.mappers.ReportRowMapper;
import com.uni.model.Client;
import com.uni.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by catal on 3/31/2017.
 */
@Repository
public class DefaultReportDAO {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public List<Report> getAllReports() {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from report;";
        return jdbcTemplate.query(SQL, new ReportRowMapper());
    }

    public List<Report> getReportsByEmployeeId(int employeeId) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from report where employeeId = " + employeeId +";";
        return jdbcTemplate.query(SQL, new ReportRowMapper());
    }

    public Report getReportById(int reportId) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "select * from report where reportId = " + reportId + ";";
        return jdbcTemplate.queryForObject(SQL, new ReportRowMapper());
    }

    public void addReport(Report report) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "insert into report values (?,?,?,?,?,?)";
        jdbcTemplate.update(SQL,report.getReportId(), report.getContent(),report.getStartDate(),report.getEndDate(),report.getEmployeeId(),report.getAdminId());
    }

    public void deleteReportByEmployeeId(int employeeId) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String SQL = "delete from report where employeeId = ?";
        jdbcTemplate.update(SQL, employeeId);
    }

}
