package org.example.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Search extends DatabaseExtention{
    public Search(Connection con) {
        super(con);
    }

    public void querryBuilder(List<String> inputs){
        for(String input : inputs){
            if(!input.contains("@")){
                input = input.toLowerCase();
                input = input.replace(input.toCharArray()[0], input.toUpperCase().toCharArray()[0]);
            }
        }
    }

    public void find(String querry) throws SQLException{
        Statement stmt = getCon().createStatement();
        ResultSet rt = stmt.executeQuery(querry);
        printResult(rt);
    }
}
