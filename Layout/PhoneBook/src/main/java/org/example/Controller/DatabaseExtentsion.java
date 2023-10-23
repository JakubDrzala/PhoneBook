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

    public String getTableName() throws SQLException {
        String[] types = {"TABLE"};
        ResultSet tableNameRs = getCon().getMetaData().getTables(null, null, "%", types);
        return tableNameRs.getString(0);
    }

    public void printResult(String querry) throws SQLException { //printing given result for debuging
        ResultSet rs = getResult(querry);
        int rowCount = getRowCount(rs);

        while (rs.next()) {
            String out = "";
            for (int x = 1; x < rowCount; x++) {
                out += rs.getString(x) + " ";
            }
            System.out.println(out);
        }
    }

    public List<String> getSearch(String querry, int id) throws SQLException { //processing querry through db and getting one result
        List<String> out = new ArrayList<>();
        ResultSet rs = getResult(querry);
        int rowCount = getRowCount(rs);

        do {
            rs.next();
        } while (rs.getRow() == id + 1);
        for (int x = 1; x < rowCount; x++) {
            out.add(rs.getString(x));
        }
        return out;
    }

    public ResultSet getResult(String querry) throws SQLException {
        //im to tired ,for this shit, but this function is basically for get ResultSet from a querry thats string, im sure that there will be multiple use cases for this function so i hope that i didnt fuck it up
        Statement stmt = getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(querry);
        return rs;
    }

    public static Connection ConnectDB() {

        String url = "jdbc:mysql://localhost:3306/ubercompany";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
            //connection.close();
        } catch (SQLException e) {
            return null;
        }
    }
}
