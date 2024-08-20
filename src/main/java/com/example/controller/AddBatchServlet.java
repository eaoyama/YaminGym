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
import java.time.LocalDate;
import java.time.LocalTime;

import com.example.database.Database;
import com.example.model.Batch;

/**
 * Servlet implementation class AddBatchServlet
 */
public class AddBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBatchServlet() {
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
		
		//Create Batch object
		Batch sendToMysql = new Batch();
		
		//Set my form data in my Batch object
		sendToMysql.setBatchName(request.getParameter("batchName"));
		
		String dayOfClassString = request.getParameter("dayOfClass");
		DayOfWeek dayOfClass = DayOfWeek.valueOf(dayOfClassString);
		sendToMysql.setDayOfClass(dayOfClass);
					
		String startHourString = request.getParameter("startHour");
		LocalTime startHour = LocalTime.parse(startHourString);
		sendToMysql.setStartHour(startHour);
		
		// Use the database singleton instance
		Database db = Database.getInstance();
		
		//SQL Query to insert new batch
		
		String sql ="insert into batch (batchName, dayOfClass, startHour) values (?, ?, ?)";
		
		try (Connection connection = db.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setString(1, sendToMysql.getBatchName());
			
			dayOfClass = sendToMysql.getDayOfClass();
			preparedStatement.setString(2, dayOfClass.name());
			
			java.sql.Time convertToSqlTime = java.sql.Time.valueOf(sendToMysql.getStartHour()); // converting to MySQL acceptable type
			preparedStatement.setTime(3, convertToSqlTime);	
			
			//Execute the insert
			int result = db.executeUpdate(preparedStatement);
			
			System.out.println(result > 0 ? "Batch created successfully. result is:" +result : "Failed to create batch. result is:" +result);
			
			if (result > 0) {
				// send a success response cuz if it's not zero, it means insert to batch
				// was success.
				// forward our client to a new page, showing the result of the insert.
				
				System.out.println("Batch created successfully. result is:" +result);

				request.setAttribute("successMessage", "Class created successfully!");
				request.setAttribute("batchId", sendToMysql.getBatchId());
				request.setAttribute("batchName", sendToMysql.getBatchName());
				request.setAttribute("dayOfClass", sendToMysql.getDayOfClass());
				request.setAttribute("startHour", sendToMysql.getStartHour());

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/addBatch.jsp");
				dispatcher.forward(request, response);

			} else {
				// send an error response
				// forward our client to an error page
				System.out.println("Failed to create batch. result is:" +result);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error while creating batch" +e);
		}
	}

}
