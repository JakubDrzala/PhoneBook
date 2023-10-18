package org.example;
import java.util.Scanner;
import java.sql.*;
public class Search {
    public static void show(){

    }
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/ubercompany";
        String username = "root";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            //insert(connection);

        Scanner sc = new Scanner(System.in);
            System.out.println("Name");
            String inName = sc.nextLine();
            if(inName.length() > 0){ inName = inName.replace(inName.toCharArray()[0], inName.toUpperCase().toCharArray()[0]); }

            System.out.println("Surname");
            String inSurname = sc.nextLine();
            if(inSurname.length() > 0){ inSurname = inSurname.replace(inSurname.toCharArray()[0], inSurname.toUpperCase().toCharArray()[0]); }

            System.out.println("Phone");
            String inPhone = sc.nextLine();

            System.out.println("Email");
            String inEmail = sc.nextLine();

            String querry = "SELECT * FROM employees WHERE ";
            String[] conditions = new String[4];
            if(!inName.equals("")){ conditions[0] = "NAME LIKE '%" + inName + "%'"; } else{ conditions[0] = ""; }
            if(!inSurname.equals("")){ conditions[1] = "SURNAME LIKE '%" + inSurname + "%'"; } else{ conditions[1] = ""; }
            if(!inPhone.equals("")){ conditions[2] = "PHONE_NUMBER LIKE '%" + inPhone + "%'";} else{ conditions[2] = ""; }
            if(!inEmail.equals("")){ conditions[3] = "EMAIL LIKE '%" + inEmail + "%'";} else{ conditions[3] = ""; }

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            while(rs.next()){
                if(rs.getString("NAME").toLowerCase().equals(inName.toLowerCase()) && !conditions[0].equals("")){
                    conditions[0] = "NAME = '" + inName + "'";
                }
                if(rs.getString("SURNAME").toLowerCase().equals(inSurname.toLowerCase()) && !conditions[1].equals("")){
                    conditions[1] = "SURNAME = '" + inSurname + "'";
                }
                if(rs.getString("PHONE_NUMBER").toLowerCase().equals(inPhone.toLowerCase()) && !conditions[2].equals("")){
                    conditions[2] = "PHONE_NUMBER = '" + inPhone + "'";
                }
                if(rs.getString("EMAIL").toLowerCase().equals(inEmail.toLowerCase()) && !conditions[3].equals("")){
                    conditions[3] = "EMAIL = '" + inEmail + "'";
                }
            }

            for(int x = 0; x < conditions.length; x++){
                if(!conditions[x].equals("")){
                    querry += conditions[x] + " AND ";
                }
            }

            querry = querry.substring(0, querry.length() - 5) + ";";

            System.out.println(querry);
            Statement finalStmt = connection.createStatement();
            ResultSet finalRs = finalStmt.executeQuery(querry);
            printResult(finalRs);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    static void printResult(ResultSet rs){
        try {
            while(rs.next()){
                String out = "";
                for(int x = 1; x < 6; x++){
                    out += rs.getString(x) + " ";
                }
                System.out.println(out);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
