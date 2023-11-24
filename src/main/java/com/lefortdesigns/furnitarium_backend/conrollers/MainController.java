package com.lefortdesigns.furnitarium_backend.conrollers;

import com.lefortdesigns.furnitarium_backend.models.Person;
import com.lefortdesigns.furnitarium_backend.security.PersonDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/aboba")
    ResponseEntity<String> hello() {
        return ResponseEntity.ok()
                .header("Custom-Header", "андрей бомжик")
                .body("https://www.ewrc.cz/images/1985/photos/lotto_bianchi_rally_1985/hse_droogmans1.jpg");
    }

    @GetMapping("/user")
    public Person getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//        System.out.println(personDetails.getPerson());
        return personDetails.getPerson();
    }
}
