package org.example.Controller;

import java.sql.*;
public class Add extends DatabaseExtentsion{
    public Add(Connection con) {
        super(con);
    }
    public static void insert(String name, String surname, String number, String email) throws SQLException{

            String query = "insert into employees values (null, '"+name+"', "+surname+"', '"+number+"', '"+email+"', '"+email+"')";
            System.out.println(query + " zostalo dodane pomyslnie");
    }
    
}
