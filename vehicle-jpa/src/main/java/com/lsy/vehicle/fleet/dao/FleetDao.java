package com.lsy.vehicle.fleet.dao;

import java.util.List;

import com.lsy.vehicle.dao.EntityDao;
import com.lsy.vehicle.fleet.domain.EngineInfo;
import com.lsy.vehicle.fleet.domain.Fleet;

public interface FleetDao extends EntityDao<Fleet> {

    public Fleet findByCompanyName(String company);

    public List<String> findAllCompanyNames();

    public List<EngineInfo> getEngineReport(String companyName);

}
