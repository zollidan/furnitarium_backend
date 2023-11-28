package com.lefortdesigns.furnitarium_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Data
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "DATE")
    private String orderDate;

    @Column
    private float totalCost;

    @Column(columnDefinition = "VARCHAR(20)")
    private String payment;

    @Column(columnDefinition = "VARCHAR(20)")
    private String paymentMethod;

    @Column
    private int productionId;

    @Column
    private int customerId;

    @Column
    private int orderStatus;

}
