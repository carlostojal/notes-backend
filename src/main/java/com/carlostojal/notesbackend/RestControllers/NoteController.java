package com.carlostojal.notesbackend.RestControllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.carlostojal.notesbackend.DBControllers.NotesDBController;
import com.carlostojal.notesbackend.DBControllers.UsersDBController;
import com.carlostojal.notesbackend.Entities.Note;

import com.carlostojal.notesbackend.Entities.User;
import com.carlostojal.notesbackend.Exceptions.NotAllowedException;
import com.carlostojal.notesbackend.Utils.Encoding;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

    // get all notes
    @GetMapping("/notes")
    ArrayList<Note> getAll(@RequestHeader String authorization) throws Exception {

        User sessionUser = UsersDBController.login(Encoding.decodeToken(authorization));
        if(sessionUser != null)
            return NotesDBController.getAll(sessionUser);
        else
            throw new NotAllowedException();
    }

    // get a note by id
    @GetMapping("/notes/{id}")
    Note getSingle(@PathVariable String id, @RequestHeader String authorization) throws Exception {

        User sessionUser = UsersDBController.login(Encoding.decodeToken(authorization));
        if(sessionUser != null)
            return NotesDBController.getById(UUID.fromString(id), sessionUser);
        else
            throw new NotAllowedException();
    }

    // create a note
    @PostMapping("/notes")
    Note create(@RequestBody Note n, @RequestHeader String authorization) throws Exception {

        User sessionUser = UsersDBController.login(Encoding.decodeToken(authorization));

        if(sessionUser != null) {
            sessionUser.setPassword(null);

            n.generateId();
            n.setCreation(new Date());
            n.setOwner(sessionUser);

            NotesDBController.create(n);

            return n;
        } else {
            throw new NotAllowedException();
        }
    }

}
