package com.carlostojal.notesbackend.DBControllers;

import com.carlostojal.notesbackend.Entities.User;
import com.carlostojal.notesbackend.Exceptions.DuplicateUserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class UsersDBController {

    public static ArrayList<User> getAll() throws SQLException {

        ArrayList<User> users = new ArrayList<>();

        Connection c = Connector.getConnection();
        Statement stm = c.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM note_user");

        while (rs.next()) {
            User u = new User();
            u.setId(UUID.fromString(rs.getString("id")));
            u.setUsername(rs.getString("username"));
            users.add(u);
        }

        rs.close();
        stm.close();
        c.close();

        return users;
    }

    public static User getById(UUID id) throws SQLException {

        User user = new User();

        Connection c = Connector.getConnection();
        PreparedStatement stm = c.prepareStatement("SELECT * FROM note_user WHERE id = ?");
        stm.setString(1, id.toString());

        ResultSet rs = stm.executeQuery();

        if(rs.next()) {
            user.setId(UUID.fromString(rs.getString("id")));
            user.setUsername(rs.getString("username"));
        } else {
            return null;
        }

        rs.close();
        stm.close();
        c.close();

        return user;
    }

    public static void create(User u) throws SQLException {

        Connection c = Connector.getConnection();
        PreparedStatement stm = c.prepareStatement("INSERT INTO note_user (id, username, password) VALUES (?, ?, ?)");
        stm.setString(1, u.getId().toString());
        stm.setString(2, u.getUsername());
        stm.setString(3, u.getPassword());
        try {
            stm.execute();
        } catch(SQLException e) {
            if(e.getMessage().contains("duplicate"))
                throw new DuplicateUserException();
            throw e;
        }

        stm.close();
        c.close();
    }

    public static void update(User u) throws SQLException {

        Connection c = Connector.getConnection();
        PreparedStatement stm = c.prepareStatement("UPDATE note_user SET password = ? WHERE id = ?");
        stm.setString(1, u.getPassword());
        stm.setString(2, u.getId().toString());
        stm.execute();

        stm.close();
        c.close();
    }

    public static User login(User u) throws SQLException {

        Connection c = Connector.getConnection();
        PreparedStatement stm = c.prepareStatement("SELECT * FROM note_user WHERE username = ? AND password = ?");
        stm.setString(1, u.getUsername());
        stm.setString(2, u.getPassword());

        ResultSet rs = stm.executeQuery();

        if(rs.next()) {
            u.setId(UUID.fromString(rs.getString("id")));
            return u;
        }

        rs.close();
        stm.close();
        c.close();

        return null;
    }
}
