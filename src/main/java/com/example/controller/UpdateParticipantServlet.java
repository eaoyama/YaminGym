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
import java.time.LocalDate;

import com.example.database.Database;
import com.example.model.Participant;

/**
 * Servlet implementation class UpdateParticipantServlet
 */
public class UpdateParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateParticipantServlet() {
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
		
		//Create placeholder Participant object
		Participant sendToMysql = new Participant();
		
		//Set my form data in my Participant object
		
		Integer pIdToInteger = Integer.parseInt(request.getParameter("pId"));
		sendToMysql.setpId(pIdToInteger);
		
		sendToMysql.setFirstName(request.getParameter("firstName"));
		sendToMysql.setLastName(request.getParameter("lastName"));
		sendToMysql.setPhone(request.getParameter("phone"));
		sendToMysql.setEmail(request.getParameter("email"));
			
		String startDateToString = request.getParameter("startDate");
		LocalDate startDate = LocalDate.parse(startDateToString);
		sendToMysql.setStartDate(startDate);

		Integer batchIdToInteger = Integer.parseInt(request.getParameter("batchId"));
		sendToMysql.setBatchId(batchIdToInteger);
		
		// Use the database singleton instance
		Database db = Database.getInstance();
		
		//SQL Query to insert new participant
		
		String sql ="update participant SET firstName=?, lastName=?, phone=?, email=?, startDate=?, batchId=? WHERE pId=?";
		
		try (Connection connection = db.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setInt(7,  sendToMysql.getpId());
			preparedStatement.setString(1, sendToMysql.getFirstName());
			preparedStatement.setString(2, sendToMysql.getLastName());
			preparedStatement.setString(3, sendToMysql.getPhone());
			preparedStatement.setString(4, sendToMysql.getEmail());
			
			java.sql.Date convertToSqlDate = java.sql.Date.valueOf(sendToMysql.getStartDate()); // converting to MySQL acceptable type
			preparedStatement.setDate(5, convertToSqlDate);	
			
			preparedStatement.setInt(6, sendToMysql.getBatchId());
			
			//Execute the insert
			int result = db.executeUpdate(preparedStatement);
			
			System.out.println(result > 0 ? "Participant updated successfully. result is:" +result : "Failed to update participant. result is:" +result);
			
			if (result > 0) {
				// send a success response cuz if it's not zero, it means update participant
				// was success.
				// forward our client to a new page, showing the result of the insert.
				
				System.out.println("Participant updated successfully. result is:" +result);

				request.setAttribute("successMessage", "Participant updated successfully!");
				request.setAttribute("firstName", sendToMysql.getFirstName());
				request.setAttribute("lastName", sendToMysql.getLastName());
				request.setAttribute("phone", sendToMysql.getPhone());
				request.setAttribute("email", sendToMysql.getEmail());
				request.setAttribute("startDate", sendToMysql.getStartDate());
				request.setAttribute("batchId", sendToMysql.getBatchId());

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/updateParticipant.jsp");
				dispatcher.forward(request, response);

			} else {
				// send an error response
				// forward our client to an error page
				System.out.println("Failed to update participant. result is:" +result);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error while updating participant" +e);
		}
		
		
	}

}
