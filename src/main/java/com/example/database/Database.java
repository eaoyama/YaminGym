package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.Batch;
import com.example.model.Participant;
import com.example.model.User;

public class Database implements DAO {

	//fields
	private Connection connection;
	
	//*******
	private static Database db = new Database();
	
	private Database() {
		// Private constructor to enforce singleton pattern
	}
	
	public static Database getInstance() {
		return db;
	}

	
	//methods
	@Override
	public Connection getConnection() {
		if (connection == null || isClosed(connection)) {
			// Re-establish the connection if it's null or closed
			connect();
		}
		return connection;
	}
	
	private void connect() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String user = "johndoe";
			String password = "password2";
			String url = "jdbc:mysql://localhost/phaseII_skill_end_projects";
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("MySQL connection created!");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while connecting to MySQL." +e);
			e.printStackTrace();
		}
	}
	
	private boolean isClosed(Connection connection) {
		try {
			return connection == null || connection.isClosed();
		} catch (SQLException e) {
			System.out.println("SQLExeption occured.");
			e.printStackTrace();
			return true;
		}
	}
	
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub	
		try {
			connection.close();
			System.out.println("MySQL connection closed!");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while closing connection." +e);
			e.printStackTrace();
		}
	}

	@Override
	public int executeUpdate(PreparedStatement preparedStatement) {
		int result= 0;
		try {
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while executeUpdate." +e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSet executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		ResultSet result=null;
			try {
			result = preparedStatement.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while executeQuery." +e);
			e.printStackTrace();
		}
		return result;
	}


}
