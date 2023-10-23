package org.example.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Search extends DatabaseExtentsion {
    public Search(Connection con) { //constructor for search based on abstract class
        super(con);
    }

    private String querryBuilder(List<String> inputs) throws SQLException{ //building querry for selecting in db
        //getting name of table to prevent dumbass customer from misspelling word EMPLOYEES in db bcs yes, and using language that we are not familiar with, bcs WHY THE FUCK NOT USE LANGUAGE THAT NO ONE GIVES A FUCK ABOUT IN FUCKING DATABASE IN WHICH EVERYTHING SHOULD BE NAMED, AND BY EVERYTHING I MEAN EVERYFUCKINGTHING, IN THE MOST INTERNATIONAL LANGUAGE WHICH IS ENGLISH

        String tableName = getTableName();

        //i am to lazy to use tokenization so i will make sure that each input is the same as data in db
        for(String input : inputs){
            input = input.toUpperCase();
        }

        ResultSet rs = getResult("SELECT * FROM " + tableName);
        String[] conditions = new String[4]; //conditions preparing
        ResultSetMetaData rsmd = rs.getMetaData(); //getting metadata for column names
        while(rs.next()){
            for (int x = 0; x < inputs.size(); x++){
                if(inputs.get(x).equals("")){ //checking if input is null, bcs if so then there is no input
                    conditions[x] = "";
                }
                else if(rs.getString(x).toUpperCase().equals(inputs.get(x))){ //checking if db has exact input in itself and if so, then make statement with "="
                    conditions[x] = rsmd.getColumnName(x + 1) + " = '" + inputs.get(x) + "'";
                }
                else{ //or else it will be LIKE
                    conditions[x] = rsmd.getColumnName(x + 1) + "LIKE '%" + inputs.get(x) + "%'";
                }
            }
        }
        String querry = "SELECT * FROM " + tableName + " WHERE"; //querry to get all of the records
        for(int x = 0; x < conditions.length; x++){
            if(!conditions[x].equals("")){ //check if condition is not null
                querry += conditions[x] + " AND "; //if not then adds condition and AND beetwen and at the end
            }
        }
        querry = querry.substring(0, querry.length() - 5); //removes last AND

        return querry; //returns querry
    }

    public Object[][] search(List<String> inputs) throws SQLException { //main search function
        String querry = querryBuilder(inputs); //builds querry based on given inputs
        List<Object> outTemp = new ArrayList<>(); //makes temporary list of lists
        ResultSet rs = getResult(querry); //gets result out of querry

        int x = 1;
        while(rs.next()){
            outTemp.add(getSearch(querry, x)); //adding record to tempoarary list
            x++;
        }

        Object[][] out = new Object[getRowCount(rs)][outTemp.size()]; //preparing for returing everything

        for (int y = 0; y < out[1].length; y++){
            for(x = 0; x < out[0].length; x++){
                out[x][y] = outTemp.get(x); //trasfering from temporary to main out
            }
        }

        return out; //returning everything in format perfect for oleks and tobaias ui
    }
}
