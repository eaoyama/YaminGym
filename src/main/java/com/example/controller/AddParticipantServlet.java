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
 * Servlet implementation class AddParticipantServlet
 */
public class AddParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddParticipantServlet() {
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
		
		//Create Participant object
		Participant sendToMysql = new Participant();
		
		//Set my form data in my Participant object
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
		
		String sql ="insert into participant (firstName, lastName, phone, email, startDate, batchId) values (?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = db.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setString(1, sendToMysql.getFirstName());
			preparedStatement.setString(2, sendToMysql.getLastName());
			preparedStatement.setString(3, sendToMysql.getPhone());
			preparedStatement.setString(4, sendToMysql.getEmail());
			
			java.sql.Date convertToSqlDate = java.sql.Date.valueOf(sendToMysql.getStartDate()); // converting to MySQL acceptable type
			preparedStatement.setDate(5, convertToSqlDate);	
			
			preparedStatement.setInt(6, sendToMysql.getBatchId());
			
			//Execute the insert
			int result = db.executeUpdate(preparedStatement);
			
			System.out.println(result > 0 ? "Participant created successfully. result is:" +result : "Failed to create participant. result is:" +result);
			
			if (result > 0) {
				// send a success response cuz if it's not zero, it means insert to participant
				// was success.
				// forward our client to a new page, showing the result of the insert.
				
				System.out.println("Participant created successfully. result is:" +result);

				request.setAttribute("successMessage", "Participant added successfully!");
				request.setAttribute("pId", sendToMysql.getpId());
				request.setAttribute("firstName", sendToMysql.getFirstName());
				request.setAttribute("lastName", sendToMysql.getLastName());
				request.setAttribute("phone", sendToMysql.getPhone());
				request.setAttribute("email", sendToMysql.getEmail());
				request.setAttribute("startDate", sendToMysql.getStartDate());
				request.setAttribute("batchId", sendToMysql.getBatchId());

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/addParticipant.jsp");
				dispatcher.forward(request, response);

			} else {
				// send an error response
				// forward our client to an error page
				System.out.println("Failed to create participant. result is:" +result);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while creating participant" +e);
			e.printStackTrace();
		}
		
		
	}

}
