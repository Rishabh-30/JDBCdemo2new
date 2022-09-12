package com.example.dao;

import com.example.data.Address;
import com.example.data.Student;
import com.example.util.DbConnection;

import java.sql.*;

public class StudentDAO {
    public int insertToStudent(Student student) throws SQLException, ClassNotFoundException {
        int rows = 0;
        //connection
        Connection connection = DbConnection.getConnection();
        String sql = "Insert into student_tbl (name,rollNo, email,  address, standard) values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement
                (sql, Statement.RETURN_GENERATED_KEYS);
        //set the values for placeholders
        //setter provided by prepared statement, using the type of data and index
        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getRollNo());
        preparedStatement.setString(3, student.getEmail());
        preparedStatement.setString(4, String.valueOf(student.getAddress()));
        preparedStatement.setString(5, String.valueOf(student.getStandard()));

        rows = preparedStatement.executeUpdate();
        int generatedId = 0;
        if (rows == 1) {
            //get generated id
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
        }
        return rows;
    }
}
