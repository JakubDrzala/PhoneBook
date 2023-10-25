package org.example.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseExtentsion {
    private Connection con; //connection

    public DatabaseExtentsion(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    } //getting connection but rn idk if this is neccesary lmao

    public int getRowCount(ResultSet rs) throws SQLException { //getting row count from resultset
        int rowCount = 0;
        rs.last();
        rowCount = rs.getRow();
        rs.beforeFirst();
        return rowCount;
    }

    public void printResult(String querry) throws SQLException { //printing given result for debuging
        ResultSet rs = getResult(querry);
        int rowCount = getRowCount(rs);

        while (rs.next()) {
            String out = "";
            for (int x = 1; x < 6; x++) {
                out += rs.getString(x) + " ";
            }
            System.out.println(out);
        }
    }


    public ResultSet getResult(String querry) throws SQLException {
        //im to tired ,for this shit, but this function is basically for get ResultSet from a querry thats string, im sure that there will be multiple use cases for this function so i hope that i didnt fuck it up
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery(querry);
    }
}
