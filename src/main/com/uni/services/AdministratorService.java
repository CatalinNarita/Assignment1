package com.uni.services;

import com.uni.dao.implementation.DefaultEmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by catal on 3/30/2017.
 */
@Service
public class AdministratorService {

    @Autowired
    private DefaultEmployeeDAO defaultEmployeeDAO;

    public DefaultEmployeeDAO getDefaultEmployeeDAO() {
        return defaultEmployeeDAO;
    }

}
