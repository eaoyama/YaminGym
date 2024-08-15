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
import com.example.model.Participant;

/**
 * Servlet implementation class DeleteParticipantServlet
 */
public class DeleteParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteParticipantServlet() {
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
		
		// Create placeholder Participant object
		Participant sendToMysql = new Participant();

		// Set my form data in my Batch object

		Integer pIdToInteger = Integer.parseInt(request.getParameter("pId"));
		sendToMysql.setpId(pIdToInteger);

		// Use the database singleton instance
		Database db = Database.getInstance();

		// SQL Query to insert new batch

		String sql = "delete from participant WHERE pId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, sendToMysql.getpId());

		// Execute the deletion
		int result = db.executeUpdate(preparedStatement);

		System.out.println(result > 0 ? "Participant deleted successfully. result is:" + result
				: "Failed to delete participant. result is:" + result);

		if (result > 0) {
				// send a success response cuz if it's not zero, it means delete participant
				// was success.
				// forward our client to a new page, showing the result of the deletion.

				System.out.println("Participant deleted successfully. result is:" + result);

				request.setAttribute("successMessage", "Participant deleted successfully!");
				request.setAttribute("pId", sendToMysql.getpId());

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteParticipant.jsp");
				dispatcher.forward(request, response);

			} else {
				// send an error response
				// forward our client to an error page
				System.out.println("Failed to delete participant. result is:" + result);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error while deleting participant" + e);
		}
	}

}
