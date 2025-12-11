package org.example.util;

import org.example.entities.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {
    // utility function to generate hashed password form plain password using Bcrypt dependency //
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // utility function to check if password is correct //
    public static boolean checkPassword(String plainPassword , String hashedPassword) {
        return BCrypt.checkpw(plainPassword,hashedPassword);
    }


}
