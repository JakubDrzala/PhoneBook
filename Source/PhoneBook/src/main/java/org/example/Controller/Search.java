package org.example.Controller;

import java.sql.*;

public class Search extends DatabaseExtention{
    public Search(Connection con) {
        super(con);
    }

    public void find(String querry) throws SQLException{
        Statement stmt = getCon().createStatement();
        ResultSet rt = stmt.executeQuery(querry);
        printResult(rt);
    }
}
