package com.carlostojal.notesbackend.DBControllers;

import com.carlostojal.notesbackend.Entities.Note;
import com.carlostojal.notesbackend.Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class NotesDBController {

    public static ArrayList<Note> getAll(User u) {
        ArrayList<Note> notes = new ArrayList<>();

        try {
            Connection c = Connector.getConnection();
            PreparedStatement stm = c.prepareStatement("SELECT * FROM note WHERE owner = ?");

            stm.setString(1, u.getId().toString());

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Note n = new Note();
                n.setId(UUID.fromString(rs.getString("id")));
                n.setText(rs.getString("text"));
                n.setLast_edit(rs.getDate("last_edit"));
                n.setCreation(rs.getDate("creation"));
                notes.add(n);
            }

            rs.close();
            stm.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public static Note getById(UUID id, User user) {
        Note note = new Note();

        try {
            Connection c = Connector.getConnection();
            PreparedStatement stm = c.prepareStatement("SELECT * FROM note WHERE id = ? AND owner = ?");
            stm.setString(1, id.toString());
            stm.setString(2, user.getId().toString());

            ResultSet rs = stm.executeQuery();

            if(rs.next()) {
                note.setText(rs.getString("text"));
                note.setLast_edit(rs.getDate("last_edit"));
                note.setCreation(rs.getDate("creation"));
            } else {
                return null;
            }

            rs.close();
            stm.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;
    }

    public static void create(Note n) {

        try {
            Connection c = Connector.getConnection();
            PreparedStatement stm = c.prepareStatement("INSERT INTO note (id, text, creation, owner) VALUES (?, ?, ?, ?)");
            stm.setString(1, n.getId().toString());
            stm.setString(2, n.getText());
            stm.setDate(3, new java.sql.Date(n.getCreation().getTime()));
            stm.setString(4, n.getOwner().getId().toString());
            stm.execute();

            stm.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
