package com.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.example.database.Database;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
       // Get the user input from the form
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");

        // SQL query to check if the user exists with the given email and password
        String sql = "SELECT * FROM user WHERE userId = ? AND userPassword = ?";
        
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                // If user is found, forward to the next page
                request.setAttribute("userFirstName", resultSet.getString("userFirstName"));
                request.setAttribute("userLastName", resultSet.getString("userLastName"));
                // Set any other user details as needed
                request.setAttribute("successMessage", "Let's ZUMBA!");
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
                dispatcher.forward(request, response);
            } else {
                // If user is not found, return to login page with error message
                request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle database errors
            request.setAttribute("errorMessage", "An error occurred during login. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
	}
		
	}