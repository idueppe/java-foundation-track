package com.lsy.vehicle.service.spi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lsy.vehicle.converter.LogEntryConverter;
import com.lsy.vehicle.dao.ApplicationLogDao;
import com.lsy.vehicle.domain.ApplicationLog;
import com.lsy.vehicle.dto.LogEntry;
import com.lsy.vehicle.service.ApplicationLogService;

@Service("applicationLog")
@Transactional(propagation=Propagation.SUPPORTS)
public class ApplicationLogServiceBean implements ApplicationLogService {

    @Autowired
    private ApplicationLogDao dao;
    
    @Autowired
    private LogEntryConverter logEntryConverter;
    
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void log(String message) {
        dao.log(new ApplicationLog(message));
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<LogEntry> logEntries() {
        return logEntryConverter.convert(dao.findAll());
    }
    
}
