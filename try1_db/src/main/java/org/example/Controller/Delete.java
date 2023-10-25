package org.example.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete extends DatabaseExtentsion{

    private int id;

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
        executeQuerry("delete from employees where id="+id+"");
    }
}
