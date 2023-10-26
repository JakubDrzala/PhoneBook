package org.example.Controller;

import java.sql.*;
import java.util.List;

public class Search extends DatabaseExtentsion {
    public Search(Connection con) { //constructor for search based on abstract class
        super(con);
    }

    private String querryBuilder(List<String> inputs) throws SQLException{ //building querry for selecting in db

        boolean isNull = true;
        //i am to lazy to use tokenization so i will make sure that each input is the same as data in db
        for(int x = 0; x < inputs.size(); x++){
            inputs.set(x, inputs.get(x).toUpperCase());
            if (!inputs.get(x).equals("")){
                isNull = false;
            }
        }

        ResultSet rs = getResult("SELECT * FROM employees");
        String querry;
        if (!isNull) {
            String[] conditions = new String[5]; //conditions preparing
            ResultSetMetaData rsmd = rs.getMetaData(); //getting metadata for column names
            while (rs.next()) {
                for (int x = 0; x < inputs.size(); x++) {
                    if (inputs.get(x).equals("")) { //checking if input is null, bcs if so then there is no input
                        conditions[x] = "";
                    }
                    else{
                        conditions[x] = " UPPER(";
                        if (rs.getString(x + 2).toUpperCase().equals(inputs.get(x))) { //checking if db has exact input in itself and if so, then make statement with "="
                            conditions[x] += rsmd.getColumnName(x + 2) + ") = '" + inputs.get(x) + "'";
                        } else { //or else it will be LIKE
                            conditions[x] += rsmd.getColumnName(x + 2) + ") LIKE '%" + inputs.get(x) + "%'";
                        }
                    }
                }
            }
            querry = "SELECT * FROM employees WHERE "; //querry to get all of the records
            for (int x = 0; x < conditions.length; x++) {
                if (!conditions[x].equals("")) { //check if condition is not null
                    querry += conditions[x] + " AND "; //if not then adds condition and AND beetwen and at the end
                }
            }
            querry = querry.substring(0, querry.length() - 5); //removes last AND
        }else{
            querry = "SELECT * FROM employees";
        }
        return querry; //returns querry
    }

    public Object[][] searchFor(List<String> inputs) throws SQLException { //main search function
        String querry = querryBuilder(inputs); //builds querry based on given inputs
        ResultSet rs = getResult(querry); //gets result out of querry

        Object[][] out = new Object[getRowCount(rs)][8];

//        rs.first();
        int y = 0;
//        do
        while(rs.next()){
            out[y][0] = rs.getInt(1);
            for(int x = 1; x < 6; x++){
                out[y][x] = rs.getString(x + 1);
            }
            out[y][6] = "edit";
            out[y][7] = "delete";
            y++;
        };

        return out;
    }

    public Object[][] getAll() throws SQLException { //main search function
        String querry = "SELECT * FROM employees"; //builds querry based on given inputs
        ResultSet rs = getResult(querry); //gets result out of querry

        Object[][] out = new Object[getRowCount(rs)][8];
//        rs.first();
        int y = 0;
//        do
        while(rs.next()){
            for(int x = 1; x < 6; x++){
                out[y][x] = rs.getString(x + 1);
            }
            out[y][6] = "edit";
            out[y][7] = "delete";
            y++;
        };

        return out;
    }

}
