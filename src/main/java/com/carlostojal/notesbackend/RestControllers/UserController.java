package com.carlostojal.notesbackend.RestControllers;

import com.carlostojal.notesbackend.DBControllers.UsersDBController;
import com.carlostojal.notesbackend.Entities.User;
import com.carlostojal.notesbackend.Exceptions.NotAllowedException;
import com.carlostojal.notesbackend.Utils.Encoding;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class UserController {

    // get all users
    @GetMapping("/users")
    ArrayList<User> getAll() throws Exception {
        return UsersDBController.getAll();
    }

    // get a user by id
    @GetMapping("/users/{id}")
    User getSingle(@PathVariable String id) throws Exception {
        return UsersDBController.getById(UUID.fromString(id));
    }

    // create a user
    @PostMapping("/users")
    User create(@RequestBody User u) throws Exception {
        u.generateID();
        UsersDBController.create(u);
        u.setPassword(null);
        return u;
    }

    @PutMapping("/users")
    User update(@RequestBody User u, @RequestHeader String authorization) throws Exception {

        User sessionUser = UsersDBController.login(Encoding.decodeToken(authorization));
        if(sessionUser != null) {
            u.setId(sessionUser.getId());
            UsersDBController.update(u);
            u.setPassword(null);
            return u;
        }

        throw new NotAllowedException();
    }
}
