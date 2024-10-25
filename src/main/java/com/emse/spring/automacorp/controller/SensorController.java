package com.emse.spring.automacorp.controller;

import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.service.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<SensorDao> findAll() {
        return sensorService.findAll();
    }

    @GetMapping("/{id}")
    public SensorDao findById(@PathVariable Long id) {
        return sensorService.findById(id).orElse(null);
    }

    @PostMapping
    public SensorDao create(@RequestBody SensorDao sensorDto) {
        return sensorService.create(sensorDto);
    }

    @PutMapping("/{id}")
    public SensorDao update(@PathVariable Long id, @RequestBody SensorDao sensorDto) {
        return sensorService.update(id, sensorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sensorService.deleteById(id);
    }
}
