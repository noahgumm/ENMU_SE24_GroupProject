package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ManageFloors", urlPatterns = "/ManageFloors")
public class ManageFloorsController extends HttpServlet{
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
        // requestDispatcher.forward(req, resp);

        resp.setContentType("text/html");

        // Write the response message, in an HTML page
        try (PrintWriter out = resp.getWriter()) {
            // Writing HTML content
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>ManageFloors</title></head>");
            out.println("<body>");
            out.println("<h1>ManageFloors</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doDelete(req, resp);
    }
}
