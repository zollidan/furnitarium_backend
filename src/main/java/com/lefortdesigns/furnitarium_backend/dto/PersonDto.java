package com.lefortdesigns.furnitarium_backend.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PersonDto {

    @Column
    private String email;
    @Column
    private String password;

}
