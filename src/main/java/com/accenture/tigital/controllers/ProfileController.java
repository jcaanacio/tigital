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
import com.accenture.tigital.libraries.typings.ProfileInput;
import com.accenture.tigital.models.Profile;
import com.accenture.tigital.services.ProfileService;


@RestController
@RequestMapping("/api/v1/users")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @BearerAuth
    @PostMapping("/{userId}/profile")
    @ResponseStatus(HttpStatus.CREATED)
    public Profile create(@PathVariable Long userId, @RequestBody ProfileInput profileInput){
        return profileService.create(userId,profileInput);
    }

    @BearerAuth
    @GetMapping("/{userId}/profile")
    public Profile get(@PathVariable Long userId){
        return profileService.getByUserId(userId);
    }

    @BearerAuth
    @DeleteMapping("/{userId}/profile/{profileId}")
    public void delete(@PathVariable Long userId,@PathVariable Long profileId){
        profileService.delete(userId, profileId);
    }

    @BearerAuth
    @PutMapping("/{userId}/profile/{profileId}")
    public Profile update(@PathVariable Long userId,@PathVariable Long profileId, @RequestBody ProfileInput profileInput){
        return profileService.update(userId, profileId, profileInput);
    }
}
