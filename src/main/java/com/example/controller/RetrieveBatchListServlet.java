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
import java.util.ArrayList;
import java.util.List;

import com.example.database.Database;
import com.example.model.Batch;

/**
 * Servlet implementation class RetrieveBatchListServlet
 */
@WebServlet("/retrieveBatchList")
public class RetrieveBatchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveBatchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Database db = Database.getInstance();
		
		List<Batch> batchList = new ArrayList<>();
		
		String sql = "select * from batch";
		
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ResultSet resultSet = ps.executeQuery();
			
			//Process the resultSet
			while (resultSet.next()) {
				Batch batch = new Batch();
				batch.setBatchId(resultSet.getInt("batchId"));
				batch.setBatchName(resultSet.getString("batchName"));
//				batch.setDayOfClass(resultSet.getString("dayOfClass"));
//				batch.setStartHour(resultSet.getTime("startHour"));
				
				String dayOfClassString = resultSet.getString("dayOfClass");
				DayOfWeek dayOfClass = DayOfWeek.valueOf(dayOfClassString.toUpperCase());
				batch.setDayOfClass(dayOfClass);

				LocalTime startHour = resultSet.getTime("startHour").toLocalTime();
				batch.setStartHour(startHour);

				batchList.add(batch);
				
			}
			
	        // Set the list of users as a request attribute
			request.setAttribute("successMessage", "Current list of classes:");
			request.setAttribute("batchList", batchList);
            

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/retrieveBatchList.jsp");

            dispatcher.forward(request, response);
            
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while retrieving batch list" +e);
			e.printStackTrace();
 
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
