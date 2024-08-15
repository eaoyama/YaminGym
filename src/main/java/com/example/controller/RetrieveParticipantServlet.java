package com.example.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import com.example.database.Database;
import com.example.model.Participant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RetrieveParticipantServlet
 */
public class RetrieveParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveParticipantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//Create Participant object
//				Participant sendToMysql = new Participant();
//				
//				//Set my form data in my Participant object
//				sendToMysql.setFirstName(request.getParameter("firstName"));
//				sendToMysql.setLastName(request.getParameter("lastName"));
//				sendToMysql.setPhone(request.getParameter("phone"));
//				sendToMysql.setEmail(request.getParameter("email"));
//					
//				String startDateToString = request.getParameter("startDate");
//				LocalDate startDate = LocalDate.parse(startDateToString);
//				sendToMysql.setStartDate(startDate);
//
//				Integer batchIdToInteger = Integer.parseInt(request.getParameter("batchId"));
//				sendToMysql.setBatchId(batchIdToInteger);
//				
//				// Use the database singleton instance
//				Database db = Database.getInstance();
//				
//				//SQL Query to insert new participant
//				
//				String sql = "select * from participant WHERE pId=?";
//				
//				try (Connection connection=db.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);){
//					ps.setInt(1, sendToMysql.getpId());
//					
//					ResultSet resultSet = db.executeQuery(ps);
//					
//					//if i successfully retrieved a result
//					if (resultSet.next()) {
//						request.setAttribute("participantID", resultSet.getInt("pId"));
//						request.setAttribute("firstName", resultSet.getInt("firstName"));
//						request.setAttribute("lastName", resultSet.getInt("lastName"));
//						
//						//create a Dispatcher and forward the information to your success page
//					}
//					
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//
//	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//Set my form data
		String pId = request.getParameter("pId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String startDate = request.getParameter("startDate");
		String batchId = request.getParameter("batchId");
        
        // Prepare SQL statement based on user selection
		
        String sql = "SELECT * FROM participant WHERE 1=1";
        if (pId != null && !pId.isEmpty()) {
            sql += " AND pId = ?";
        } else if (firstName != null && !firstName.isEmpty()) {
            sql += " AND firstName LIKE ?";
        } else if (lastName != null && !lastName.isEmpty()) {
            sql += " AND lastName LIKE ?";
        } else if (startDate != null && !startDate.isEmpty()) {
            sql += " AND startDate >= ?";
        } else if (batchId != null && !batchId.isEmpty()) {
            sql += " AND batchId = ?";
        }
		
		        
        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

               int paramIndex = 1;
               if (pId != null && !pId.isEmpty()) {
                   ps.setInt(paramIndex++, Integer.parseInt(pId));
               } else if (firstName != null && !firstName.isEmpty()) {
                   ps.setString(paramIndex++, "%" + firstName + "%");
               } else if (lastName != null && !lastName.isEmpty()) {
                   ps.setString(paramIndex++, "%" + lastName + "%");
               } else if (startDate != null && !startDate.isEmpty()) {
                   ps.setDate(paramIndex++, java.sql.Date.valueOf(LocalDate.parse(startDate)));
               } else if (batchId != null && !batchId.isEmpty()) {
                   ps.setInt(paramIndex++, Integer.parseInt(batchId));
               }
               
               ResultSet resultSet = ps.executeQuery();
               
//				try (Connection connection=db.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);){
//				ps.setInt(1, sendToMysql.getpId());
//				
//				ResultSet resultSet = db.executeQuery(ps);
//				
//				//if i successfully retrieved a result
//				if (resultSet.next()) {
//					request.setAttribute("pID", resultSet.getInt("pId"));
//					request.setAttribute("firstName", resultSet.getInt("firstName"));
//					request.setAttribute("lastName", resultSet.getInt("lastName"));
//					
//					//create a Dispatcher and forward the information to your success page
//				}



               // Check if a participant was found and set attributes
               if (resultSet.next()) {
                   request.setAttribute("pId", resultSet.getInt("pId"));
                   request.setAttribute("firstName", resultSet.getString("firstName"));
                   request.setAttribute("lastName", resultSet.getString("lastName"));
                   request.setAttribute("startDate", resultSet.getDate("startDate").toString());
                   request.setAttribute("batchId", resultSet.getInt("batchId"));

                   // Forward to JSP page to display the participant data
                   RequestDispatcher dispatcher = request.getRequestDispatcher("retrieveParticipant.jsp");
                   dispatcher.forward(request, response);
               } else {
                   // Handle case where no participant is found
                   response.getWriter().println("No participant found with the provided criteria.");
               }

           } catch (Exception e) {
               e.printStackTrace();
               response.getWriter().println("An error occurred while retrieving the participant.");
           }

      		
	}

}
