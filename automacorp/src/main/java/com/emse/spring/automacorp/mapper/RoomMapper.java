package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.entity.RoomEntity;

import java.util.stream.Collectors;

public class RoomMapper {
    public static RoomDao of(RoomEntity entity) {
        return new RoomDao(
                entity.getId(),
                entity.getName(),
                entity.getWindows().stream().map(WindowMapper::of).collect(Collectors.toList())
        );
    }
}
