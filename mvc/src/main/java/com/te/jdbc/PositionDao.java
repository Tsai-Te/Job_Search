package com.te.jdbc;

import com.te.domain.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDao {
    static final String DB_url="localhost:1234/jobsearch_dev";
    static final String DB_username="mac";
    static final String DB_password="123456";

    public List<Position> getUsers(){
        List<Position> positions=new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try{
            System.out.println("Connecting database...");
            connection= DriverManager.getConnection(DB_url, DB_username, DB_password);

            System.out.println("Creating statement...");
            statement=connection.createStatement();
            String sql="SELECT * FROM position";
            resultSet=statement.executeQuery(sql);

            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String auditor=resultSet.getString("auditor");
                String engineer=resultSet.getString("engineer");
                String manager=resultSet.getString("manager");

                Position position=new Position();
                position.setAuditor(auditor);
                position.setEngineer(engineer);
                position.setManager(manager);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(resultSet!=null)
                    resultSet.close();
                if(statement!=null)
                    resultSet.close();
                if(connection!=null)
                    resultSet.close();
            }
            catch (SQLException se){
                se.printStackTrace();
            }
        }
        return positions;
    }
}
