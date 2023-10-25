package org.example.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionsManager extends DatabaseExtentsion{
    public PermissionsManager(Connection con){ super(con); }
    /*

        Funkcja check password działa tak
        >Pobiera wpisany login i hasło
        >Porównuje z obecnymi loginami hasłami w bazie danych
        >Jeśli login i hasło odpowiada Adminowi to zwraca "ADMIN"
        >Jeśli login i hasło odpowiada Userowi to zwraca "USER"
        >A jeśli nie pasuje do żadnego to zwraca "EXIT"
        Zrób tak, aby przy zwróceniu "ADMIN" odblokowywało wszystkie przyciski
        natomiast przy zwróceniu "USER" blokowało wszystkie przyciski poza SEARCH
        a przy zwróceniu "EXIT" wypierdalało program

     */

    public String checkPassword(String login, String password) throws SQLException {
        String querry = "SELECT login, password, type FROM users";
        ResultSet rs = getResult(querry);

        while(rs.next()){
            if(rs.getString("login").equals(login) && rs.getString("password").equals(password)){
                return rs.getString("type").toUpperCase(); //returning type of user
            }
        }

        return "EXIT"; //if login or password is wrong then program will exit
    }
}
