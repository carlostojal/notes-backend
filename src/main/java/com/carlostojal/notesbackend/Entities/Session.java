package com.carlostojal.notesbackend.Entities;

import java.util.Date;
import java.util.UUID;

public class Session {

    public Session() {
        this.generateID();
    }

    public Session(User user) {
        this.generateID();
        this.user = user;
    }

    private UUID id;
    private User user;
    private Date expiry;

    public UUID getId() {
        return id;
    }

    public void setId(UUID session_id) {
        this.id = session_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void generateID() {
        this.id = UUID.randomUUID();
    }
}
