package com.carlostojal.notesbackend.RestControllers;

import com.carlostojal.notesbackend.DBControllers.UsersDBController;
import com.carlostojal.notesbackend.Entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class UserController {

    // get all users
    @GetMapping("/users")
    ArrayList<User> getAll() {
        return UsersDBController.getAll();
    }

    // get a user by id
    @GetMapping("/users/{id}")
    User getSingle(@PathVariable String id) {
        return UsersDBController.getById(UUID.fromString(id));
    }

    // create a user
    @PostMapping("/users")
    User create(@RequestBody User u) {
        u.generateID();
        UsersDBController.create(u);
        u.setPassword(null);
        return u;
    }
}
