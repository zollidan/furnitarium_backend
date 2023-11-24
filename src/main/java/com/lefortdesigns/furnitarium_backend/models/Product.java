package com.lefortdesigns.furnitarium_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "products")
@Data
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private float price;

    @Column
    private float company_price;

    @Column
    private float sale_price;

    @Column
    private String product_name;


    @Column
    private String product_image;

    @Column
    private int category_id;

    @Column
    private String product_description;

    @Column
    private String product_properties;

}
