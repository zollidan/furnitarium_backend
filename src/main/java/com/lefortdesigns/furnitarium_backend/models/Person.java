package com.lefortdesigns.furnitarium_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String role;
//    @Column
//    private boolean newsletter;


}
