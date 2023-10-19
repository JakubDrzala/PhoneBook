package org.example.Controller;

import java.sql.*;
import java.util.List;

public class Search extends DatabaseExtentsion {
    public Search(Connection con) {
        super(con);
    }

    public void querryBuilder(List<String> inputs){
        for(String input : inputs){
            input = input.toUpperCase();
        }
    }

    public void find(String querry) throws SQLException{
        Statement stmt = getCon().createStatement();
        ResultSet rt = stmt.executeQuery(querry);
        printResult(rt);
    }
}
