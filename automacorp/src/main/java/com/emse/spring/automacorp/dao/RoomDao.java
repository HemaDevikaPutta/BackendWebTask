package com.emse.spring.automacorp.dao;

import java.util.List;

public record RoomDao(Long id, String name, List<WindowDao> windowDao) {
}
