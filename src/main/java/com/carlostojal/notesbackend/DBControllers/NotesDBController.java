package com.carlostojal.notesbackend.DBControllers;

import com.carlostojal.notesbackend.Entities.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class NotesDBController {

    public static ArrayList<Note> getAll() {
        ArrayList<Note> notes = new ArrayList<>();

        try {
            Connection c = Connector.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM note");

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

    public static Note getById(UUID id) {
        Note note = new Note();

        try {
            Connection c = Connector.getConnection();
            PreparedStatement stm = c.prepareStatement("SELECT * FROM note WHERE id = ?");
            stm.setString(1, id.toString());

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
            PreparedStatement stm = c.prepareStatement("INSERT INTO note (id, text, creation) VALUES (?, ?, ?)");
            stm.setString(1, n.getId().toString());
            stm.setString(2, n.getText());
            stm.setDate(3, new java.sql.Date(n.getCreation().getTime()));
            stm.execute();

            stm.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
