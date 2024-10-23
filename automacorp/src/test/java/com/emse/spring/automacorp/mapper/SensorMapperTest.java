package com.emse.spring.automacorp.mapper;


import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.entity.SensorEntity;
import com.emse.spring.automacorp.entity.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensorMapperTest {

    @Test
    void shouldMapSensorEntityToDto() {
        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setId(1L);
        sensorEntity.setName("Temperature Sensor");
        sensorEntity.setSensorType(SensorType.TEMPERATURE);
        sensorEntity.setValue(25.0);

        SensorDao sensorDto = SensorMapper.of(sensorEntity);

        assertEquals(1L, sensorDto.id());
        assertEquals("Temperature Sensor", sensorDto.name());
        assertEquals(SensorType.TEMPERATURE, sensorDto.sensorType());
        assertEquals(25.0, sensorDto.value());
    }
}
