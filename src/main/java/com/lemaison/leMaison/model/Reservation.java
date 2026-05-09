package com.lemaison.leMaison.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
    @NotBlank(message = "Name is required")
    private String customerName;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9+\\-\\s]{7,15}$", message = "Enter a valid phone number")
    private String phone;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate reservationDate;

    @Min(value = 1, message = "At least 1 seat required")
    private int seats;

    public Reservation() {}

    public Long getId()                    { return id; }
    public String getCustomerName()        { return customerName; }
    public String getPhone()               { return phone; }
    public LocalDate getReservationDate()  { return reservationDate; }
    public int getSeats()                  { return seats; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setPhone(String phone)               { this.phone = phone; }
    public void setReservationDate(LocalDate d)      { this.reservationDate = d; }
    public void setSeats(int seats)                  { this.seats = seats; }
}
