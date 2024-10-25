package com.emse.spring.automacorp.controller;

import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.entity.SensorType;
import com.emse.spring.automacorp.service.SensorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SensorController.class)
class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorService sensorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAllSensors() throws Exception {
        SensorDao sensor1 = new SensorDao(1L, "Temperature Sensor", 25.0, SensorType.TEMPERATURE);
        SensorDao sensor2 = new SensorDao(2L, "Humidity Sensor", 45.0, SensorType.HUMIDITY);

        Mockito.when(sensorService.findAll()).thenReturn(List.of(sensor1, sensor2));

        mockMvc.perform(get("/api/sensors").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Temperature Sensor"))
                .andExpect(jsonPath("[1].name").value("Humidity Sensor"));
    }

    @Test
    void shouldFindSensorById() throws Exception {
        SensorDao sensorDto = new SensorDao(1L, "Temperature Sensor", 25.0, SensorType.TEMPERATURE);
        Mockito.when(sensorService.findById(1L)).thenReturn(java.util.Optional.of(sensorDto));

        mockMvc.perform(get("/api/sensors/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Temperature Sensor"));
    }

    @Test
    void shouldCreateSensor() throws Exception {
        SensorDao sensorDto = new SensorDao(null, "Temperature Sensor", 25.0, SensorType.TEMPERATURE);
        SensorDao savedSensor = new SensorDao(1L, "Temperature Sensor", 25.0, SensorType.TEMPERATURE);

        Mockito.when(sensorService.create(Mockito.any(SensorDao.class))).thenReturn(savedSensor);

        mockMvc.perform(post("/api/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Temperature Sensor"));
    }

    @Test
    void shouldDeleteSensor() throws Exception {
        mockMvc.perform(delete("/api/sensors/1"))
                .andExpect(status().isOk());

        Mockito.verify(sensorService, Mockito.times(1)).deleteById(1L);
    }
}
