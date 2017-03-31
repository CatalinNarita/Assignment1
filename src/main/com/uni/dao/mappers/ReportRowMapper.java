package com.uni.dao.mappers;

import com.uni.model.Report;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by catal on 3/31/2017.
 */
public class ReportRowMapper implements RowMapper<Report> {
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report report = new Report();

        report.setReportId(rs.getInt(1));
        report.setContent(rs.getString(2));
        report.setStartDate(rs.getDate(3));
        report.setEndDate(rs.getDate(4));
        report.setEmployeeId(rs.getInt(5));
        report.setAdminId(rs.getInt(6));

        return report;
    }
}
