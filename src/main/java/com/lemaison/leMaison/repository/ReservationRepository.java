package com.lemaison.leMaison.repository;

import com.lemaison.leMaison.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT COALESCE(SUM(r.seats), 0) FROM Reservation r WHERE r.reservationDate = :date")
    int sumSeatsByDate(@Param("date") LocalDate date);
}
