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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.database.Database;
import com.example.model.Participant;

/**
 * Servlet implementation class RetrieveParticipantListServlet
 */
@WebServlet("/retrieveParticipantList")
public class RetrieveParticipantListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveParticipantListServlet() {
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
		
		List<Participant> participantList = new ArrayList<>();
		
        String sql = "SELECT p.*, b.batchName FROM participant p JOIN batch b ON p.batchId = b.batchId";
		
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ResultSet resultSet = ps.executeQuery();
			
			//Process the resultSet
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
			
	        // Set the list of users as a request attribute
			request.setAttribute("successMessage", "Current list of participants:");
			request.setAttribute("participantList", participantList);
            

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/retrieveParticipantList.jsp");
            dispatcher.forward(request, response);
            
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while retrieving participant list" +e);
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
