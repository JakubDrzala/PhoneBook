package org.example.Controller;

import java.sql.*;
public class Add extends DatabaseExtentsion{
    public Add(Connection con) {
        super(con);
    }
    public void insert(String name, String surname, String number, String email) throws SQLException{
        executeQuerry("insert into employees (name, surname, phone_nr, email) values ('"+name+"', '"+surname+"', '"+number+"', '"+email+"')");
    }
}
