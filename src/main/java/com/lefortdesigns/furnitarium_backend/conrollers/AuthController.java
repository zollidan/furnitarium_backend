package com.lefortdesigns.furnitarium_backend.conrollers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lefortdesigns.furnitarium_backend.dto.AuthenticationDto;
import com.lefortdesigns.furnitarium_backend.dto.PersonDto;
import com.lefortdesigns.furnitarium_backend.models.Person;
import com.lefortdesigns.furnitarium_backend.security.JwtUtil;
import com.lefortdesigns.furnitarium_backend.services.RegistrationService;
import com.lefortdesigns.furnitarium_backend.util.PersonValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    public AuthController(PersonValidator personValidator, RegistrationService registrationService, JwtUtil jwtUtil,
            ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/registration")
    public Map<String, String> registrationPerfome(@RequestBody @Valid PersonDto personDto,
            BindingResult bindingResult) {

        Person person = covertToPerson(personDto);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("error", "error of registration");
        }

        registrationService.registration(person);

        String token = jwtUtil.generateToken(person.getEmail());

        return Map.of("jwt-token", token);
    }

    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // он авторизует всех на похуй надо поправить
    @PostMapping("/login")
    public Map<String, String> performingLogin(@RequestBody AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authenticationInputToken = new UsernamePasswordAuthenticationToken(
                authenticationDto.getEmail(),
                authenticationDto.getPassword());

        try {
            authenticationManager.authenticate(authenticationInputToken);
        } catch (BadCredentialsException exception) {
            Map.of("message", "bad credential");
        }

        String token = jwtUtil.generateToken(authenticationDto.getEmail());
        return Map.of("jwt-token", token);
    }

    public Person covertToPerson(PersonDto personDto) {
        return this.modelMapper.map(personDto, Person.class);
    }
}
