package com.lsy.vehicle.service.spi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.vehicle.dao.ManufacturerDao;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.service.ManufacturerAlreadyExistsException;
import com.lsy.vehicle.service.ManufacturerService;

@Service
public class ManufacturerServiceBean implements ManufacturerService {

    @Autowired
    private ManufacturerDao manuDao;
     
    @Override
    public List<Manufacturer> findAll() {
        return manuDao.findAll();
    }

    @Override
    public void addManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException {
        if (isExisting(manufacturerName)) {
            throw new ManufacturerAlreadyExistsException(manufacturerName);
        }
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerName);
        manuDao.create(manufacturer);
    }

    public boolean isExisting(String manufacturerName) {
        return manuDao.findManufacturerByName(manufacturerName) != null;
    }

    @Override
    public Manufacturer byName(String manufacturerName) {
        return manuDao.findManufacturerByName(manufacturerName);
    }

	@Override
	public void delete(Manufacturer manufacturer) {
		manuDao.delete(manufacturer);
	}

    @Override
    public void updateName(Long manufacturerId, String newManufacturerName) {
        Manufacturer manufacturer = manuDao.find(manufacturerId);
        manufacturer.setName(newManufacturerName);
        manuDao.update(manufacturer); // optional
    }

}
