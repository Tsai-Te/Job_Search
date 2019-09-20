package com.te.jdbc;

import com.te.domain.MyJob;
import com.te.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyJobDao {
    static final String DB_URL="localhost:1234/jobsearch_dev";
    static final String DB_USERNAME="mac";
    static final String DB_PASSWORD="123456";

    public List<MyJob> getMyJobs(){
        List<MyJob> myJobs=new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try{
            System.out.println("Connecting to database...");
            connection= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            System.out.println("Creating statement...");
            statement=connection.createStatement();
            String sql="SELECT * FROM my_jobs";
            resultSet=statement.executeQuery(sql);

            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String savedJobs=resultSet.getString("saved_jobs");

                MyJob myJob=new MyJob();
                myJob.setSavedJobs(savedJobs);
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
        return myJobs;
    }
}
