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
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import com.example.database.Database;

/**
 * Servlet implementation class RetrieveBatchServlet
 */
public class RetrieveBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveBatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String batchId = request.getParameter("batchId");
		String batchName = request.getParameter("batchName");
		
		String dayOfClassString = request.getParameter("dayOfClass");
		// Convert the dayOfClass to match the MySQL ENUM format (First letter uppercase)
		String dayOfClass = null;
		if (dayOfClassString!=null && !dayOfClassString.trim().isEmpty()) {
			dayOfClass = dayOfClassString.substring(0, 1).toUpperCase() + dayOfClassString.substring(1).toLowerCase();
		}

//		DayOfWeek dayOfClass = DayOfWeek.valueOf(dayOfClassString);
		
//		String startHourString = request.getParameter("startHour");
//		LocalTime startHour = LocalTime.parse(startHourString);
		
		String startHourString = request.getParameter("startHour");
		LocalTime startHour = null;
		if (startHourString != null && !startHourString.trim().isEmpty()) {
		    startHour = LocalTime.parse(startHourString);
		}
		
		Database db = Database.getInstance();
		
        // Prepare SQL statement based on user selection
		String sql = "SELECT batchId, batchName, dayOfClass, startHour FROM batch WHERE 1=1";
        if (batchId != null && !batchId.isEmpty()) {
            sql += " AND batchId = ?";
        } 
        if (batchName != null && !batchName.isEmpty()) {
            sql += " AND batchName LIKE ?";
        } 
        if (dayOfClass != null && !dayOfClass.isEmpty()) {
            sql += " AND dayOfClass LIKE ?";
        } 
        if (startHour != null) {
            sql += " AND startHour = ?";
        }
		
        try (Connection connection = db.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

               int paramIndex = 1;
               if (batchId != null && !batchId.isEmpty()) {
            	   preparedStatement.setInt(paramIndex++, Integer.parseInt(batchId));
               } 
               if (batchName != null && !batchName.isEmpty()) {
            	   preparedStatement.setString(paramIndex++, "%" + batchName + "%");
               } 
               if (dayOfClass != null && !dayOfClass.isEmpty()) {
            	   preparedStatement.setString(paramIndex++, "%" + dayOfClass + "%");
               } 
               if (startHour != null) {
            	   preparedStatement.setTime(paramIndex++, java.sql.Time.valueOf(startHour));
               } 
               
               ResultSet resultSet = preparedStatement.executeQuery();
               
               // Check if a batch was found and set attributes
               if (resultSet.next()) {
                   request.setAttribute("batchId", resultSet.getInt("batchId"));
                   request.setAttribute("batchName", resultSet.getString("batchName"));
                   request.setAttribute("dayOfClass", resultSet.getString("dayOfClass"));
                   request.setAttribute("startHour", resultSet.getTime("startHour").toString());

                   // Forward to JSP page to display the batch data
                   RequestDispatcher dispatcher = request.getRequestDispatcher("retrieveBatch.jsp");
                   dispatcher.forward(request, response);
               } else {
                   // Handle case where no batch is found
                   response.getWriter().println("No class found with the provided criteria.");
               }

           } catch (Exception e) {
        	   System.out.println("Error while retrieving batch" +e);
        	   e.printStackTrace();

           }

	}

}
