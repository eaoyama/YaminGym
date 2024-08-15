package com.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.database.Database;
import com.example.model.User;

/**
 * Servlet implementation class AddUserServlet
 */
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//Create User object
		User sendToMysql = new User();
		
		//Set my form data in my User object
		sendToMysql.setUserFirstName(request.getParameter("userFirstName"));
		sendToMysql.setUserLastName(request.getParameter("userLastName"));
		sendToMysql.setUserEmail(request.getParameter("userEmail"));
		sendToMysql.setUserPassword(request.getParameter("userPassword"));
				
		// Use the database singleton instance
		Database db = Database.getInstance();
		
		//SQL Query to insert new user
		
		String sql ="insert into user (userFirstName, userLastName, userEmail, userPassword) values (?, ?, ?, ?)";
		
		try (Connection connection = db.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setString(1, sendToMysql.getUserFirstName());
			preparedStatement.setString(2, sendToMysql.getUserLastName());
			preparedStatement.setString(3, sendToMysql.getUserEmail());
			preparedStatement.setString(4, sendToMysql.getUserPassword());
						
			//Execute the insert
			int result = db.executeUpdate(preparedStatement);
			
			System.out.println(result > 0 ? "User created successfully. result is:" +result : "Failed to create user. result is:" +result);
			
			if (result > 0) {
				// send a success response cuz if it's not zero, it means insert to user
				// was success.
				// forward our client to a new page, showing the result of the insert.
				
				System.out.println("User created successfully. result is:" +result);

				request.setAttribute("successMessage", "User added successfully!");
				request.setAttribute("userFirstName", sendToMysql.getUserFirstName());
				request.setAttribute("userLastName", sendToMysql.getUserLastName());
				request.setAttribute("userEmail", sendToMysql.getUserEmail());
				request.setAttribute("userPassword", sendToMysql.getUserPassword());

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/addUser.jsp");
				dispatcher.forward(request, response);

			} else {
				// send an error response
				// forward our client to an error page
				System.out.println("Failed to create user. result is:" +result);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error while creating user" +e);
		}

		
	}

}
