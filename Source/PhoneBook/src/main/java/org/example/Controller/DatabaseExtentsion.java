package org.example.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseExtentsion {
    private Connection con; //connection
    public DatabaseExtentsion(Connection con){
        this.con = con;
    }
    public Connection getCon(){
        return con;
    } //getting connection but rn idk if this is neccesary lmao
    private int getRowCount(ResultSet rs) throws SQLException{
        int rowCount = 0;
        rs.last();
        rowCount = rs.getRow();
        rs.beforeFirst();
        return rowCount;
    }
    public void printResult(String querry) throws SQLException { //printing given result
        ResultSet rs = getResult(querry);
        int rowCount = getRowCount(rs);

        while(rs.next()){
            String out = "";
            for(int x = 1; x < rowCount; x++){
                out += rs.getString(x) + " ";
            }
            System.out.println(out);
        }
    }
    public List<String> getColumn(String querry, int column) throws SQLException{
        List<String> out = new ArrayList<>();

        ResultSet rs = getResult(querry);
        int rowCount = getRowCount(rs);

        System.out.println("sperma");
        System.out.println(rs.getString(column) + " CUM");

        return out;
    }
    private ResultSet getResult(String querry) throws SQLException{
        //im to tired ,for this shit, but this function is basically for get ResultSet from a querry thats string, im sure that there will be multiple use cases for this function so i hope that i didnt fuck it up
        Statement stmt = getCon().createStatement();
        ResultSet rs = stmt.executeQuery(querry);
        return rs;
    }
}
