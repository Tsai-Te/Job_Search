package com.te.jdbc;

import com.te.domain.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDao {
    static final String DB_URL="localhost:1234/jobsearch_dev";
    static final String DB_USERNAME="mac";
    static final String DB_PASSWORD="123456";

    public List<Region> getUsers(){
        List<Region> regions=new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try{
            System.out.println("Connecting to database...");
            connection= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            System.out.println("Creating statement...");
            statement=connection.createStatement();
            String sql="SELECT * FROM region";
            resultSet=statement.executeQuery(sql);

            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String state=resultSet.getString("state");
                String city=resultSet.getString("city");
                int zipCode=resultSet.getInt("zip_code");

                Region region=new Region();
                region.setState(state);
                region.setCity(city);
                region.setZipCode(zipCode);
                //todo position
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
        return regions;
    }
}
