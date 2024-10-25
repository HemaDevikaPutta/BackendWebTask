package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.entity.WindowStatus;

public record WindowDao(Long id, String name, WindowStatus windowStatus, Long roomId) {
}