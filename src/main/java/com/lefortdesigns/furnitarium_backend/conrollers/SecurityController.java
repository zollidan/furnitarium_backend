package com.lefortdesigns.furnitarium_backend.conrollers;

import com.lefortdesigns.furnitarium_backend.models.Person;
import com.lefortdesigns.furnitarium_backend.security.JwtUtil;
import com.lefortdesigns.furnitarium_backend.security.PersonDetails;
import com.lefortdesigns.furnitarium_backend.services.RegService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    private final RegService regService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public SecurityController(RegService regService, JwtUtil jwtUtil, ModelMapper modelMapper,
                              AuthenticationManager authenticationManager) {
        this.regService = regService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/reg")
    public Map<String, String> postReg(@RequestBody Person person) {
        regService.register(person);

        String token = jwtUtil.generateToken(person.getEmail());

        return Map.of("jwt-token", token);
    }

    @GetMapping("/user")
    public Person getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        // System.out.println(personDetails.getPerson());
        return personDetails.getPerson();
    }

}
