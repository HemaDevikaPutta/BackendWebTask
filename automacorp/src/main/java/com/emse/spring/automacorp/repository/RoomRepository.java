package com.emse.spring.automacorp.repository;

import com.emse.spring.automacorp.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
