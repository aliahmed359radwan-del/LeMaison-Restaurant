package com.lemaison.leMaison.service;

import com.lemaison.leMaison.model.Reservation;
import com.lemaison.leMaison.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationService {

    private static final int MAX_SEATS_PER_DAY = 50;

    private final ReservationRepository reservationRepo;

    public ReservationService(ReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public int getAvailableSeats(LocalDate date) {
        int booked = reservationRepo.sumSeatsByDate(date);
        return Math.max(0, MAX_SEATS_PER_DAY - booked);
    }

    public Reservation save(Reservation reservation) {
        int available = getAvailableSeats(reservation.getReservationDate());
        if (reservation.getSeats() > available) {
            throw new IllegalStateException(
                "Only " + available + " seats available on " + reservation.getReservationDate()
            );
        }
        return reservationRepo.save(reservation);
    }
}
