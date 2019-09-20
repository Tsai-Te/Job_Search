package com.te.jdbc;

import com.te.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    static final String DB_URL="localhost:1234/jobsearch_dev";
    static final String DB_USERNAME="mac";
    static final String DB_PASSWORD="123456";

    public List<User> getUsers(){
        List<User> users=new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try{
            System.out.println("Connecting to database...");
            connection= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); //try needs catch, so there was a compile error at "getConnection"

            System.out.println("Creating statement...");
            statement=connection.createStatement();
            String sql="SELECT * FROM user";
            resultSet=statement.executeQuery(sql);

            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String username=resultSet.getString("username");
                String firstName=resultSet.getString("first_name");
                String lastName=resultSet.getString("last_name");
                String email=resultSet.getString("email");

                User user=new User();
                user.setUsername(username);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
            }
        }
        catch(Exception e){
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
        return users;
    }
}
