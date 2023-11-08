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
import com.accenture.tigital.libraries.typings.NoteInput;
import com.accenture.tigital.models.Note;
import com.accenture.tigital.services.NoteService;


@RestController
@RequestMapping("/api/v1/users")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @BearerAuth
    @PostMapping("/{userId}/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public Note create(@PathVariable Long userId, @RequestBody NoteInput noteInput){
        return noteService.create(userId, noteInput);
    }

    @BearerAuth
    @GetMapping("/{userId}/notes")
    public Note get(@PathVariable Long userId){
        return noteService.getByUserId(userId);
    }

    @BearerAuth
    @PutMapping("/{userId}/notes/{noteId}")
    public Note update(@PathVariable Long userId, @PathVariable Long noteId, @RequestBody NoteInput noteInput){
        return noteService.update(userId, noteId, noteInput);
    }
}
