package com.lemaison.leMaison.controller;

import com.lemaison.leMaison.model.Reservation;
import com.lemaison.leMaison.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String form(Model model,
                       @RequestParam(required = false)
                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkDate) {
        model.addAttribute("reservation", new Reservation());

        LocalDate dateToCheck = (checkDate != null) ? checkDate : LocalDate.now();
        model.addAttribute("checkDate", dateToCheck);
        model.addAttribute("availableSeats", reservationService.getAvailableSeats(dateToCheck));

        return "reservation";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("reservation") Reservation reservation,
                       BindingResult result,
                       Model model) {

        LocalDate date = reservation.getReservationDate() != null
            ? reservation.getReservationDate() : LocalDate.now();

        model.addAttribute("checkDate", date);
        model.addAttribute("availableSeats", reservationService.getAvailableSeats(date));

        if (result.hasErrors()) {
            return "reservation";
        }

        try {
            reservationService.save(reservation);
            model.addAttribute("successMessage",
                "✅ Reservation confirmed for " + reservation.getCustomerName() +
                " on " + reservation.getReservationDate() + " (" + reservation.getSeats() + " seats)!");
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("availableSeats",
                reservationService.getAvailableSeats(date));
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", "❌ " + e.getMessage());
        }

        return "reservation";
    }
}
