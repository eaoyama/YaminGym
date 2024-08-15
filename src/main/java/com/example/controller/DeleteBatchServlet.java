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
import java.time.DayOfWeek;
import java.time.LocalTime;

import com.example.database.Database;
import com.example.model.Batch;

/**
 * Servlet implementation class DeleteBatchServlet
 */
public class DeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBatchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		// Create placeholder Batch object
		Batch sendToMysql = new Batch();

		// Set my form data in my Batch object

		Integer batchIdToInteger = Integer.parseInt(request.getParameter("batchId"));
		sendToMysql.setBatchId(batchIdToInteger);

		// Use the database singleton instance
		Database db = Database.getInstance();

		// SQL Query to insert new batch

		String sql = "delete from batch WHERE batchId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, sendToMysql.getBatchId());

		// Execute the deletion
		int result = db.executeUpdate(preparedStatement);

		System.out.println(result > 0 ? "Batch deleted successfully. result is:" + result
				: "Failed to delete batch. result is:" + result);

		if (result > 0) {
				// send a success response cuz if it's not zero, it means delete batch
				// was success.
				// forward our client to a new page, showing the result of the deletion.

				System.out.println("Batch deleted successfully. result is:" + result);

				request.setAttribute("successMessage", "Batch deleted successfully!");
				request.setAttribute("batchId", sendToMysql.getBatchId());

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteBatch.jsp");
				dispatcher.forward(request, response);

			} else {
				// send an error response
				// forward our client to an error page
				System.out.println("Failed to delete batch. result is:" + result);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error while deleting batch" + e);
		}

	}

}
