package com.accenture.tigital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.tigital.libraries.decorators.BearerAuth;
import com.accenture.tigital.libraries.typings.RaffleInput;
import com.accenture.tigital.models.Raffle;
import com.accenture.tigital.services.RaffleService;


@RestController
@RequestMapping("/api/v1/users/{userId}/raffles")
public class RaffleController {
    @Autowired
    private RaffleService raffleService;

    @BearerAuth
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Raffle create(@PathVariable Long userId, @RequestBody RaffleInput raffleInput){
        return raffleService.create(userId, raffleInput);
    }

    @BearerAuth
    @GetMapping("")
    public Raffle get(@PathVariable Long userId){
        return raffleService.getByUserId(userId);
    }

    @BearerAuth
    @PutMapping("/{raffleId}")
    public Raffle update(@PathVariable Long userId, @PathVariable Long raffleId, @RequestBody RaffleInput raffleInput){
        return raffleService.update(userId, raffleId, raffleInput);
    }
}
