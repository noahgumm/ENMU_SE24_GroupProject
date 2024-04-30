package com.hotelreservationapp.controllers;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.Database.Tables.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Home", urlPatterns = "/Home")
public class HomeController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve the User object from the session
		User user = (User) request.getSession().getAttribute("user");
		
		// Get the user's name for greeting
		String name = user.getUsername();
		
		// Set the name as an attribute for retrieval by the view		
		request.setAttribute("name", name);
        request.getRequestDispatcher("mainView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
		// Invalidate the session to log out the user
		request.getSession().invalidate();
		
		// Redirect to the login page
		request.getRequestDispatcher("").forward(request, response);
    }
}