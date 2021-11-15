package com.tripsgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tripsgo.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
