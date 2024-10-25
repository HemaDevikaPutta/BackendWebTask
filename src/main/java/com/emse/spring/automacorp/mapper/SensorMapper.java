package com.emse.spring.automacorp.mapper;


import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.entity.SensorEntity;

public class SensorMapper {

    public static SensorDao of(SensorEntity entity) {
        return new SensorDao(
                entity.getId(),
                entity.getName(),
                entity.getValue(),
                entity.getSensorType()
        );
    }

    public static SensorEntity toEntity(SensorDao dto) {
        SensorEntity entity = new SensorEntity();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setValue(dto.value());
        entity.setSensorType(dto.sensorType());
        return entity;
    }
}
