package com.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestType = request.getParameter("type");
		RequestDispatcher dispatcher = null;
		
		if (requestType.equals("login")) {
			dispatcher = request.getRequestDispatcher("login");			
		} else if (requestType.equals("register")){
			dispatcher = request.getRequestDispatcher("register");
		} else if (requestType.equals("logout")){
			dispatcher = request.getRequestDispatcher("logout");
		} else if (requestType.equals("createParticipant")){
			dispatcher = request.getRequestDispatcher("createParticipant");
		} else if (requestType.equals("updateParticipant")){
			dispatcher = request.getRequestDispatcher("updateParticipant");
		} else if (requestType.equals("getParticipant")){
			dispatcher = request.getRequestDispatcher("getParticipant");
		} else if (requestType.equals("deleteParticipant")){
			dispatcher = request.getRequestDispatcher("deleteParticipant");
		} else {
			dispatcher = request.getRequestDispatcher("error");
		}
		
		dispatcher.forward(request, response);
	}

}
