package com.example.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
		//Set my form data
		String pId = request.getParameter("pId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String startDate = request.getParameter("startDate");
		String batchId = request.getParameter("batchId");
		String batchName = request.getParameter("batchName");
        
		// Use the database singleton instance
		Database db = Database.getInstance();
		
        // Prepare SQL statement based on user selection
        String sql = "SELECT p.*, b.batchName FROM participant p JOIN batch b ON p.batchId = b.batchId WHERE 1=1";
        
        if (pId != null && !pId.isEmpty()) {
            sql += " AND p.pId = ?";
            String searchCriteria = pId;
            request.setAttribute("searchCriteria",searchCriteria);
        } 
        if (firstName != null && !firstName.isEmpty()) {
            sql += " AND p.firstName LIKE ?";
            String searchCriteria = firstName;
            request.setAttribute("searchCriteria",searchCriteria);
        } 
        if (lastName != null && !lastName.isEmpty()) {
            sql += " AND p.lastName LIKE ?";
            String searchCriteria = lastName;
            request.setAttribute("searchCriteria",searchCriteria);
        } 
        if (startDate != null && !startDate.isEmpty()) {
            sql += " AND p.startDate >= ?";
            String searchCriteria = startDate;
            request.setAttribute("searchCriteria",searchCriteria);
        } 
        if (batchId != null && !batchId.isEmpty()) {
            sql += " AND p.batchId = ?";
            String searchCriteria = batchId;
            request.setAttribute("searchCriteria",searchCriteria);
        }
        
        if (batchName != null && !batchName.isEmpty()) {
            sql += " AND b.batchName LIKE ?";
        	String searchCriteria = batchName;
            request.setAttribute("searchCriteria",searchCriteria);
        }
		
		        
        try (Connection connection = db.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

               int paramIndex = 1;
               if (pId != null && !pId.isEmpty()) {
            	   preparedStatement.setInt(paramIndex++, Integer.parseInt(pId));
               } 
               if (firstName != null && !firstName.isEmpty()) {
            	   preparedStatement.setString(paramIndex++, "%" + firstName + "%");
               } 
               if (lastName != null && !lastName.isEmpty()) {
            	   preparedStatement.setString(paramIndex++, "%" + lastName + "%");
               } 
               if (startDate != null && !startDate.isEmpty()) {
            	   preparedStatement.setDate(paramIndex++, java.sql.Date.valueOf(LocalDate.parse(startDate)));
               } 
               if (batchId != null && !batchId.isEmpty()) {
            	   preparedStatement.setInt(paramIndex++, Integer.parseInt(batchId));
               }
               if (batchName != null && !batchName.isEmpty()) {
            	   preparedStatement.setString(paramIndex++,  "%" + batchName + "%");
               }

               ResultSet resultSet = preparedStatement.executeQuery();
               
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

               
               // Create a list to hold the participant objects
               List<Participant> participantList = new ArrayList<>();

               // Check if a participant was found and set attributes
               while (resultSet.next()) {
                   Participant participant = new Participant();

                   participant.setpId(resultSet.getInt("pId"));
                   participant.setFirstName(resultSet.getString("firstName"));
                   participant.setLastName(resultSet.getString("lastName"));
                   participant.setPhone(resultSet.getString("phone"));
                   participant.setEmail(resultSet.getString("email"));
                   participant.setStartDate(resultSet.getDate("startDate").toLocalDate());
                   participant.setBatchId(resultSet.getInt("batchId"));
                   participant.setBatchName(resultSet.getString("batchName"));
                   
                   participantList.add(participant);
                   
               } 
               
               request.setAttribute("participantList", participantList);
               
               // Forward to JSP page to display the participant data               
               if (participantList.isEmpty()) {
            	    response.getWriter().println("No participant found with the provided criteria.");
            	} else {
            	    request.setAttribute("participantList", participantList);
            	    RequestDispatcher dispatcher = request.getRequestDispatcher("retrieveParticipant.jsp");
            	    dispatcher.forward(request, response);
            	}
               
           } catch (Exception e) {
      		System.out.println("Error while retrieving participant" +e);
        	  e.printStackTrace();
              response.getWriter().println("No participant found with the provided criteria.");

           }

      		
	}

}
