package com.carlostojal.notesbackend.Utils;

import com.carlostojal.notesbackend.Entities.User;

import java.util.Base64;

public class Encoding {

    public static User decodeToken(String token) {
        // token is of basic access authentication type
        // (Basic "user:password" base 64 encoded)

        // only get the token part
        token = token.split(" ")[1];

        String decodedString = new String(Base64.getDecoder().decode(token));
        // split username / password
        String[] credentials = decodedString.split(":");

        return new User(null, credentials[0], credentials[1]);
    }
}
