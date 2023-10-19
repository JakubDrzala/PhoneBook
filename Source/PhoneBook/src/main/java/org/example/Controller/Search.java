package org.example.Controller;

import java.sql.*;
import java.util.List;

public class Search extends DatabaseExtentsion {
    public Search(Connection con) { //constructor for search based on abstract class
        super(con);
    }

    public String querryBuilder(List<String> inputs) throws SQLException{ //building querry for selecting in db
        //getting name of table to prevent dumbass customer from misspelling word EMPLOYEES in db bcs yes, and using language that we are not familiar with, bcs WHY THE FUCK NOT USE LANGUAGE THAT NO ONE GIVES A FUCK ABOUT IN FUCKING DATABASE IN WHICH EVERYTHING SHOULD BE NAMED, AND BY EVERYTHING I MEAN EVERYFUCKINGTHING, IN THE MOST INTERNATIONAL LANGUAGE WHICH IS ENGLISH
        String[] types = {"TABLE"};
        ResultSet tableNameRs = getCon().getMetaData().getTables(null, null, "%", types);
        String tableName = tableNameRs.getString(0);

        //i am to lazy to use tokenization so i will make sure that each input is the same as data in db
        for(String input : inputs){
            input = input.toUpperCase();
        }
        return null;

    }
}
