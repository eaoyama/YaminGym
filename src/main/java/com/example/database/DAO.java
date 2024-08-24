package com.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.Batch;
import com.example.model.Participant;
import com.example.model.User;

public interface DAO {
	
    // Method to obtain the singleton instance of the database.
	//This prevents creation on multiple Database object so that there is only one entry way to database.
    static Database getInstance() {
        return Database.getInstance();
    }
    
	//method to connect to MySQL database
	Connection getConnection();
	
	//method to close connection to MySQL database
	void closeConnection();
	 

    // Method to execute an update (e.g., insert, update, delete)
    int executeUpdate(PreparedStatement preparedStatement);
    
    // Method to execute a query (e.g., select)
    ResultSet executeQuery(PreparedStatement preparedStatement);

}
