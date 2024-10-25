package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.entity.RoomEntity;
import com.emse.spring.automacorp.entity.WindowEntity;
import com.emse.spring.automacorp.entity.WindowStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WindowMapperTest {

    @Test
    void shouldMapWindowEntityToDto() {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(1L);

        WindowEntity windowEntity = new WindowEntity();
        windowEntity.setId(1L);
        windowEntity.setName("Window 1");
        windowEntity.setWindowStatus(WindowStatus.CLOSED);
        windowEntity.setRoom(roomEntity);

        WindowDao window = WindowMapper.of(windowEntity);

        assertEquals(1L, window.id());
        assertEquals("Window 1", window.name());
        assertEquals(WindowStatus.CLOSED, window.windowStatus());
        assertEquals(1L, window.roomId());
    }
}