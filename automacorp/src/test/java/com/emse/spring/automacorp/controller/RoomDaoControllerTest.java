package com.emse.spring.automacorp.controller;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.entity.RoomEntity;
import com.emse.spring.automacorp.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
class RoomDaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAllRooms() throws Exception {
        RoomEntity room1 = new RoomEntity();
        room1.setId(1L);
        room1.setName("Room 1");

        RoomEntity room2 = new RoomEntity();
        room2.setId(2L);
        room2.setName("Room 2");

        Mockito.when(roomService.findAll()).thenReturn(List.of(room1, room2));

        mockMvc.perform(get("/api/rooms").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Room 1"))
                .andExpect(jsonPath("[1].name").value("Room 2"));
    }


    @Test
    void shouldFindRoomById() throws Exception {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(1L);
        roomEntity.setName("Room 1");

        Mockito.when(roomService.findById(1L)).thenReturn(Optional.of(roomEntity));

        mockMvc.perform(get("/api/rooms/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Room 1"));
    }

    @Test
    void shouldReturnNotFoundForInvalidRoomId() throws Exception {
        Mockito.when(roomService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/rooms/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateRoom() throws Exception {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(1L);
        roomEntity.setName("Room 1");

        Mockito.when(roomService.create(Mockito.any())).thenReturn(roomEntity);

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RoomDao(null, "Room 1", List.of()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Room 1"));
    }

    @Test
    void shouldUpdateRoom() throws Exception {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(1L);
        roomEntity.setName("Updated Room");

        Mockito.when(roomService.update(Mockito.anyLong(), Mockito.any())).thenReturn(roomEntity);

        mockMvc.perform(put("/api/rooms/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RoomDao(1L, "Updated Room", List.of()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Room"));
    }

    @Test
    void shouldDeleteRoom() throws Exception {
        mockMvc.perform(delete("/api/rooms/1"))
                .andExpect(status().isOk());

        Mockito.verify(roomService, Mockito.times(1)).delete(1L);
    }
}
