package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.entity.RoomEntity;
import com.emse.spring.automacorp.entity.WindowEntity;
import com.emse.spring.automacorp.dao.WindowDao;

public class WindowMapper {

    public static WindowDao of(WindowEntity entity) {
        return new WindowDao(
                entity.getId(),
                entity.getName(),
                entity.getWindowStatus(),
                entity.getRoom() != null ? entity.getRoom().getId() : null
        );
    }

    public static WindowEntity toEntity(WindowDao dto, RoomEntity roomEntity) {
        WindowEntity entity = new WindowEntity();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setWindowStatus(dto.windowStatus());
        entity.setRoom(roomEntity);
        return entity;
    }
}
