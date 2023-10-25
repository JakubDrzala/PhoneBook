package org.example.Controller;

import java.sql.*;
public class Add extends DatabaseExtentsion{
    private Statement stm = null;
    public Add(Connection con) {
        super(con);
    }
    public void insert(String name, String surname, String number, String email) throws SQLException{
        String query = "insert into employees (name, surname, phone_nr, email) values ('"+name+"', '"+surname+"', '"+number+"', '"+email+"')";
        stm = getCon().createStatement();
        stm.execute(query);
        stm.close();
    }
}
