package org.example.Controller;

import java.sql.*;
import java.util.List;

public class Search extends DatabaseExtention {
    public Search(Connection con) { //constructor for search based on abstract class
        super(con);
    }

    public void querryBuilder(List<String> inputs) throws SQLException{ //building querry for selecting in db
        //getting name of table to prevent dumbass customer from misspelling word EMPLOYEES in db bcs yes, and using language that we are not familiar with, bcs WHY THE FUCK NOT USE LANGUAGE THAT NO ONE GIVES A FUCK ABOUT IN FUCKING DATABASE IN WHICH EVERYTHING SHOULD BE NAMED, AND BY EVERYTHING I MEAN EVERYFUCKINGTHING, IN THE MOST INTERNATIONAL LANGUAGE WHICH IS ENGLISH
        String[] types = {"TABLE"};
        ResultSet tableNameRs = getCon().getMetaData().getTables(null, null, "%", types);
        String tableName = tableNameRs.getString(0);

        for(String input : inputs){
            input = input.toUpperCase();
        }
    }

    public void find(String querry) throws SQLException{ //passing builded querry to printResult function
        Statement stmt = getCon().createStatement();
        ResultSet rt = stmt.executeQuery(querry);
        printResult(rt);
    }
}
