package org.example;

import org.example.Controller.Search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ubercompany";
        String username = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url, username, password);
        //up here is basic db connection that will be used often so dont fuck this up

        System.out.println("SPERMA");
        Search search = new Search(con);
        //and here we have objects that are basically core for our project, each programmed by different co-workers so i really hope that this will cooperate
        List<String> gowno = search.getColumn("SELECT * from employees", 3);
    }
}