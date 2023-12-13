package com.lefortdesigns.furnitarium_backend.conrollers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lefortdesigns.furnitarium_backend.security.PersonDetails;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello on webpage";
    }

    @GetMapping("/say")
    public String saysmth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        return personDetails.getUsername();
    }
}
