package com.lefortdesigns.furnitarium_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
@Data
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition="DATE")
    private String order_date;

    @Column
    private float total_cost;

    @Column(columnDefinition="VARCHAR(20)")
    private String payment;

    @Column(columnDefinition="VARCHAR(20)")
    private String payment_method;

    @Column
    private int production_id;

    @Column
    private int customer_id;

}
