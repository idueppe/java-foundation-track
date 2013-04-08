package com.lsy.vehicle.ui.managers;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.lsy.vehicle.bootstrap.DBInitialization;

@ManagedBean(eager = true)
@ApplicationScoped
public class ApplicationManager {

    private static final Logger LOG = Logger.getLogger(ApplicationManager.class.getName());

    @ManagedProperty("#{DBInitialization}")
    private DBInitialization dbInitialization;

    @PostConstruct
    public void initDatabase() {
        LOG.info("Initializing Database");
        dbInitialization.initializeDatabase();
    }

    public void setDbInitialization(DBInitialization dbInitialization) {
        this.dbInitialization = dbInitialization;
    }

}
