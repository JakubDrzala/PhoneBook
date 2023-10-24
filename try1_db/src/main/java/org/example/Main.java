package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            String dbURL = "jdbc:derby:MyDbTest;create=true";
            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            // Extract data from result set
            while (rs.next())
            {
                // Retrieve by column name
                int id  = rs.getInt("id");
                String name = rs.getString("name");

                // Display values
                System.out.print("ID: " + id);
                System.out.println(", Name: " + name);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            // TODO handle exception
            e.printStackTrace();
        }
        finally
        {
            // Clean-up environment
            try
            {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}