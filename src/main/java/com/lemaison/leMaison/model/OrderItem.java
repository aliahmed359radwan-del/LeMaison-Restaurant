package com.lemaison.leMaison.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String menuItemName;
    private int quantity;
    private double pricePerUnit;

    public OrderItem() {}

    public OrderItem(Order order, String menuItemName, int quantity, double pricePerUnit) {
        this.order = order;
        this.menuItemName = menuItemName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public Long getId()            { return id; }
    public Order getOrder()        { return order; }
    public String getMenuItemName(){ return menuItemName; }
    public int getQuantity()       { return quantity; }
    public double getPricePerUnit(){ return pricePerUnit; }
    public double getSubtotal()    { return quantity * pricePerUnit; }

    public void setOrder(Order o)          { this.order = o; }
    public void setMenuItemName(String v)  { this.menuItemName = v; }
    public void setQuantity(int v)         { this.quantity = v; }
    public void setPricePerUnit(double v)  { this.pricePerUnit = v; }
}
//s
