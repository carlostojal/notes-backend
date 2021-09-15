package com.carlostojal.notesbackend.Entities;

import java.util.Date;
import java.util.UUID;

public class Note {

    public Note() {
        this.generateId();
        this.last_edit = null;
        this.creation = new Date();
        this.owner = null;
    }

    public Note(UUID id, String text, Date last_edit, Date creation, User owner) {
        this.id = id;
        this.text = text;
        this.last_edit = last_edit;
        this.creation = creation;
        this.owner = owner;
    }

    private UUID id;
    private String text;
    private Date last_edit;
    private Date creation;
    private User owner;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLast_edit() {
        return last_edit;
    }

    public void setLast_edit(Date last_edit) {
        this.last_edit = last_edit;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void generateId() {
        this.id = UUID.randomUUID();
    }
}
