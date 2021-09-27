package com.carlostojal.notesbackend.Entities;

import java.util.Date;
import java.util.UUID;

public class RealtimeNoteUpdate extends Note {

    public RealtimeNoteUpdate(String authorization) {
        super();
        this.authorization = authorization;
    }

    public RealtimeNoteUpdate(UUID id, String text, Date last_edit, Date creation, User owner, String authorization) {
        super(id, text, last_edit, creation, owner);
        this.authorization = authorization;
    }

    private String authorization;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
