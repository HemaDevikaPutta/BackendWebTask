package com.emse.spring.automacorp.repository;

import com.emse.spring.automacorp.entity.SensorEntity;
import com.emse.spring.automacorp.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SensorRepository extends JpaRepository<SensorEntity, Long> {
    List<SensorEntity> findBySensorType(SensorType sensorType);
}