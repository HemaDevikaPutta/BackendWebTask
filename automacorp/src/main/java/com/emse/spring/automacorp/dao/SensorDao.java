package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.entity.SensorType;

public record SensorDao(Long id, String name, Double value, SensorType sensorType) {
}