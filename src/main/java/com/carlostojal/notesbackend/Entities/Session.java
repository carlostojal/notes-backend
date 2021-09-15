package com.carlostojal.notesbackend.Entities;

import java.util.Date;
import java.util.UUID;

public class Session {

    public Session() {}

    public Session(User user) {
        this.session_id = UUID.randomUUID();
        this.user = user;
    }

    private UUID session_id;
    private User user;
    private Date expiry;

    public UUID getSession_id() {
        return session_id;
    }

    public void setSession_id(UUID session_id) {
        this.session_id = session_id;
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
}
