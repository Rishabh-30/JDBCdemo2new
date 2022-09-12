package com.example.dao;

import com.example.data.Address;
import com.example.data.Standard;
import com.example.util.DbConnection;

import java.sql.*;

public class AddressDAO {
    // standard table name as class_tbl
    //insert
    public int insertIntoAddress(Address address) throws SQLException, ClassNotFoundException {
        int rows = 0;
        //connection
        Connection connection = DbConnection.getConnection();

        String sql = "Insert into address_tbl (flatNo,buildingName,street,city,state,pinCode,country) values(?,?,?,?,?,?,?)";
        // ? is placeholder, it has an index, starting form 1
        PreparedStatement preparedStatement = connection.prepareStatement
                (sql, Statement.RETURN_GENERATED_KEYS);
        //set the values for placeholders
        //setter provided by prepared statement, using the type of data and index
        preparedStatement.setString(1, address.getFlatNo());
        preparedStatement.setString(2, address.getBuildingName());
        preparedStatement.setString(3, address.getStreet());
        preparedStatement.setString(4, address.getCity());
        preparedStatement.setString(5, address.getState());
        preparedStatement.setInt(6,address.getPinCode());
        preparedStatement.setString(7, address.getCountry());


        rows = preparedStatement.executeUpdate();
        int generatedId = 0;
        if(rows == 1){
            //get generated id
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                generatedId = generatedKeys.getInt(1);
            }
        }
        return rows;
    }
    //update
    //delete
    //search
}
