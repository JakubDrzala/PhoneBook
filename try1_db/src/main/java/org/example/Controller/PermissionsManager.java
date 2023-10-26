package org.example.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionsManager extends DatabaseExtentsion{
    public PermissionsManager(Connection con){ super(con); }

    public String checkPassword(String login, String password) throws SQLException {
        Encrypt e = new Encrypt();
        password = e.encrypt(password);
        String querry = "SELECT login, password, type FROM users";
        ResultSet rs = getResult(querry);

        while(rs.next()){
            if(rs.getString("login").equals(login) && rs.getString("password").equals(password)){
                return rs.getString("type").toUpperCase(); //returning type of user
            }
        }

        return "EXIT"; //if login or password is wrong then program will exit
    }
}
