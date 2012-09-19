package com.lsy.vehicle.converter;

import org.springframework.stereotype.Service;

import com.lsy.vehicle.domain.Engine;
import com.lsy.vehicle.dto.EngineDto;

/**
 * @author idueppe
 */
@Service("engineConverter")
public class EngineConverter extends AbstractDefaultConverter<Engine, EngineDto>{

    @Override
    protected EngineDto newTargetInstance() {
        return new EngineDto();
    }

    @Override
    protected void copyProperties(Engine source, EngineDto target) {
        target.setEngineId(source.getId());
        target.setEngineType(source.getType());
    }

}
