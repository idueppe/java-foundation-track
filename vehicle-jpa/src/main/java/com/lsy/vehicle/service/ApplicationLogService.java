package com.lsy.vehicle.service;

import java.util.List;

import com.lsy.vehicle.dto.LogEntry;

public interface ApplicationLogService {
    
    public void log(String message);
    
    public List<LogEntry> logEntries();

}
