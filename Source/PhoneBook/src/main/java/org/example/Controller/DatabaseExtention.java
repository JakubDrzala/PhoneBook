package org.example.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseExtention {
    private Connection con;
    public DatabaseExtention(Connection con){
        this.con = con;
    }
    public Connection getCon(){
        return con;
    }
    public void printResult(ResultSet rs) throws SQLException {
        int rowCount = 0;
        rs.last();
        rowCount = rs.getRow();
        rs.beforeFirst();

        while(rs.next()){
            String out = "";
            for(int x = 1; x < rowCount; x++){
                out += rs.getString(x) + " ";
            }
            System.out.println(out);
        }
    }
}
