package org.example.Controller;

import java.sql.*;
public class Add extends DatabaseExtentsion{
    public Add(Connection con) { //constructor for search based on abstract class
        super(con);
    }
    public static void insert(String name, String surname, String number, String email){

        //Connection conn=ConnectDB();
        try{
            String query = "insert into customer values (null, '"+name+"', "+surname+"', '"+number+"', '"+email+"', '"+email+"')";


        }catch(Exception e){
            System.out.println("coś jebło");
        }
    }
}
