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
import java.util.ArrayList;
import java.util.List;

import com.example.database.Database;
import com.example.model.User;

/**
 * Servlet implementation class RetrieveUserListServlet
 */

@WebServlet("/retrieveUserList")
public class RetrieveUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveUserListServlet() {
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
		
		List<User> userList = new ArrayList<>();
		
		String sql = "select * from user";
		
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ResultSet resultSet = ps.executeQuery();
			
			//Process the resultSet
			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setUserFirstName(resultSet.getString("userFirstName"));
				user.setUserLastName(resultSet.getString("userLastName"));
				user.setUserEmail(resultSet.getString("userEmail"));
				user.setUserPassword(resultSet.getString("userPassword"));
				
				userList.add(user);
				
			}
			
	        // Set the list of users as a request attribute
			request.setAttribute("successMessage", "Current list of admin users:");
			request.setAttribute("userList", userList);
            

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("retrieveUserList.jsp");
            dispatcher.forward(request, response);
            
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while retrieving user" +e);
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
