package com.lefortdesigns.furnitarium_backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    @NotEmpty(message = "You must enter email.")
    private String email;
    private String password;
}
