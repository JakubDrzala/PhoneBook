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

        String dbURL = "jdbc:derby:C:\\Users\\kkile\\OneDrive\\Pulpit\\PhoneBook_repo\\Layout\\PhoneBook\\src\\lib\\employees;create=true";
        Connection conn = DriverManager.getConnection(dbURL);
        //up here is basic db connection that will be used often so dont fuck this up

        Search search = new Search(conn);
        //and here we have objects that are basically core for our project, each programmed by different co-workers so i really hope that this will cooperate
        List<String> gowno = search.getSearch("SELECT * from employees", 3);
        System.out.println(gowno.get(1));
    }
}