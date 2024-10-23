package com.emse.spring.automacorp.controller;

import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.service.WindowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/windows")
public class WindowController {

    private final WindowService windowService;

    public WindowController(WindowService windowService) {
        this.windowService = windowService;
    }

    @GetMapping
    public List<WindowDao> findAll() {
        return windowService.findAll();
    }

    @GetMapping("/{id}")
    public WindowDao findById(@PathVariable Long id) {
        return windowService.findById(id).orElse(null);
    }

    @PostMapping
    public WindowDao create(@RequestBody WindowDao windowDao) {
        return windowService.create(windowDao);
    }

    @PutMapping("/{id}")
    public WindowDao update(@PathVariable Long id, @RequestBody WindowDao windowDao) {
        return windowService.update(id, windowDao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        windowService.deleteWindow(id);
    }
}
