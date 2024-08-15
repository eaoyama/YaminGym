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
import com.example.model.Batch;
import com.example.model.User;

/**
 * Servlet implementation class DeleteUserServlet
 */
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
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
		
		// Create placeholder Batch object
		User sendToMysql = new User();

		// Set my form data in my Batch object

		Integer userIdToInteger = Integer.parseInt(request.getParameter("userId"));
		sendToMysql.setUserId(userIdToInteger);

		// Use the database singleton instance
		Database db = Database.getInstance();

		// SQL Query to insert new batch

		String sql = "delete from user WHERE userId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, sendToMysql.getUserId());

		// Execute the deletion
		int result = db.executeUpdate(preparedStatement);

		System.out.println(result > 0 ? "User deleted successfully. result is:" + result
				: "Failed to delete user. result is:" + result);

		if (result > 0) {
				// send a success response cuz if it's not zero, it means delete user
				// was success.
				// forward our client to a new page, showing the result of the deletion.

				System.out.println("User deleted successfully. result is:" + result);

				request.setAttribute("successMessage", "User deleted successfully!");
				request.setAttribute("userId", sendToMysql.getUserId());

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteUser.jsp");
				dispatcher.forward(request, response);

			} else {
				// send an error response
				// forward our client to an error page
				System.out.println("Failed to delete user. result is:" + result);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error while deleting user" + e);
		}

	}

}
