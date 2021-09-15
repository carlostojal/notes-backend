package com.carlostojal.notesbackend.RestControllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.carlostojal.notesbackend.DBControllers.NotesDBController;
import com.carlostojal.notesbackend.Entities.Note;

import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

    // get all notes
    @GetMapping("/notes")
    ArrayList<Note> getAll() {
        return NotesDBController.getAll();
    }

    // get a note by id
    @GetMapping("/notes/{id}")
    Note getSingle(@PathVariable String id) {
        return NotesDBController.getById(UUID.fromString(id));
    }

    // create a note
    @PostMapping("/notes")
    Note create(@RequestBody Note n) {

        n.generateId();
        n.setCreation(new Date());

        NotesDBController.create(n);

        return n;

    }


}
