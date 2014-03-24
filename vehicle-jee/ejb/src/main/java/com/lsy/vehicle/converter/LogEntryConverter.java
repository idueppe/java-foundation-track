package com.lsy.vehicle.converter;

import com.lsy.vehicle.domain.ApplicationLog;
import com.lsy.vehicle.dto.LogEntry;

public class LogEntryConverter extends AbstractDefaultConverter<ApplicationLog, LogEntry>{

    @Override
    protected LogEntry newTargetInstance() {
        return new LogEntry();
    }

    @Override
    protected void copyProperties(ApplicationLog source, LogEntry target) {
        target.setMessage(source.getMessage());
        target.setTimeStamp(source.getTimestamp());
    }

}
