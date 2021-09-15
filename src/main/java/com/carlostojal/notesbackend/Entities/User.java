package com.carlostojal.notesbackend.Entities;

import java.util.UUID;

public class User {

    public User() {
        this.generateID();
        this.password = null;
    }

    public User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    private UUID id;
    private String username;
    private String password;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void generateID() {
        this.id = UUID.randomUUID();
    }
}
