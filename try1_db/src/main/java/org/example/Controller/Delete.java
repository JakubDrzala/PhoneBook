package org.example.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete extends DatabaseExtentsion{

    private int id;
    private Statement stm = null;

    public Delete(Connection con){
        super(con);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(int id){
        return id;
    }

    public void delete() throws SQLException{
        String del_query = "delete from employees where id='"+id+"';";
        stm = getCon().createStatement();
        stm.execute(del_query);
        stm.close();
    }
}
