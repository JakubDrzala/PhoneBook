package org.example.Controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class Modify extends DatabaseExtentsion{
    private int id;
    private Statement stm = null;

    public Modify(Connection con){
        super(con);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(int id){
        return id;
    }

    public void modify(String new_name, String new_surname, String new_phone, String new_email) throws SQLException{
        String mod_query = "update employees set name='"+new_name+"', surname='"+new_surname+"', phone_nr='"+new_phone+
                "', email= '"+new_email+"' WHERE id='"+id+"';";
        stm = getCon().createStatement();
        stm.execute(mod_query);
        stm.close();
    }
}
