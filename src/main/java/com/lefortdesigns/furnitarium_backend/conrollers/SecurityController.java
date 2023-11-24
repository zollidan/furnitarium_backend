package com.lefortdesigns.furnitarium_backend.conrollers;

import com.lefortdesigns.furnitarium_backend.dto.AuthDto;
import com.lefortdesigns.furnitarium_backend.dto.PersonDto;
import com.lefortdesigns.furnitarium_backend.models.Person;
import com.lefortdesigns.furnitarium_backend.security.JwtUtil;
import com.lefortdesigns.furnitarium_backend.services.RegService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public SecurityController(RegService regService, JwtUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.regService = regService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login_process")
    public Map<String, Object> perfomLogin(@RequestBody AuthDto authDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (BadCredentialsException e){
            return Map.of("message", "BadCredentialsException");
        }

        String token = jwtUtil.generateToken(authDto.getUsername());
        return Map.of("jwt-token", token);
    }



    @PostMapping("/reg")
    public Map<String, String > postReg(@RequestBody Person person){
        regService.register(person);

        String token = jwtUtil.generateToken(person.getEmail());



        return Map.of("jwt-token", token);
    }

    public Person convertToPerson(PersonDto personDto){
        return this.modelMapper.map(personDto, Person.class);
    }

}
