package org.example.Controller;

import java.sql.*;
public class Add extends DatabaseExtentsion{
    private Statement stm = null;
    public Add(Connection con) {
        super(con);
    }
    public void insert(String name, String surname, String number, String email){

        try{
            String query = "insert into employees (name, surname, phone_nr, email) values ('"+name+"', '"+surname+"', '"+number+"', '"+email+"')";
            stm = getCon().createStatement();
            stm.execute(query);
            stm.close();
            System.out.println(query + " zostalo dodane pomyslnie");

        }catch(Exception e){
            System.out.println(e);
        }
    }

}
