package com.lemaison.leMaison.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  //

    @NotBlank(message = "Name is required")
    private String customerName;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9+\\-\\s]{7,15}$", message = "Enter a valid phone number")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    private LocalDateTime orderDateTime;
    private double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}

    public Long getId()                   { return id; }
    public String getCustomerName()       { return customerName; }
    public String getPhone()              { return phone; }
    public String getAddress()            { return address; }
    public LocalDateTime getOrderDateTime(){ return orderDateTime; }
    public double getTotalPrice()         { return totalPrice; }
    public List<OrderItem> getItems()     { return items; }

    public void setCustomerName(String v) { this.customerName = v; }
    public void setPhone(String v)        { this.phone = v; }
    public void setAddress(String v)      { this.address = v; }
    public void setOrderDateTime(LocalDateTime v) { this.orderDateTime = v; }
    public void setTotalPrice(double v)   { this.totalPrice = v; }
    public void setItems(List<OrderItem> v){ this.items = v; }
}
