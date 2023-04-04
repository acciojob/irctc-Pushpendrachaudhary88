package com.driver.controllers;


import com.driver.model.Passenger;
import com.driver.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @PostMapping("/create")
    public Integer registerPassenger(@RequestBody Passenger passenger){
        return passengerService.addPassenger(passenger);
    }
}
