package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.hotelreservationapp.models.Database.Prepared.CartInformation;
import com.hotelreservationapp.models.Database.Tables.User;
import com.hotelreservationapp.models.Database.Tables.UserPaymentMethod;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.Time;


@WebServlet(name = "Cart", urlPatterns = "/Cart")
public class CartController extends BaseController {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendToLoginPageIfNotLoggedIn(req, resp);
        
        DatabaseManager db = new DatabaseManager();
        //is a user logged in? get the logged in user.
        User user = getSessionUser(req);
        // User user = getSessionUserOrSendToLoginPage(req, resp); //this will redirect to login if not logged in
        if(user == null){ //for testing
            user = db.userDbManager.getUser(4);
            setSessionUser(req, user);
        }
        //get the cart for the logged in user


        CartInformation cart = db.getCartInformationFor(user);
        req.setAttribute("cart", cart);
        // resp.sendRedirect("cartView.jsp");
        req.getRequestDispatcher("cartView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String cardType = req.getParameter("cardType");
        String cardNumber = req.getParameter("cardNumber");
        String expirationDate = req.getParameter("expirationDate");
        String cvv = req.getParameter("cvv");
        String cardHolderName = req.getParameter("cardHolderName");

        //get the user
        User user = getSessionUser(req);
        // User user = getSessionUserOrSendToLoginPage(req, resp); //this will redirect to login if not logged in
        if(user == null){ //for testing
            DatabaseManager db = new DatabaseManager();
            user = db.userDbManager.getUser(4);
            setSessionUser(req, user);
        }

        //get the cart for the logged in user
        DatabaseManager db = new DatabaseManager();
        UserPaymentMethod paymentMethod = new UserPaymentMethod
        (
            0, 
            cardNumber, 
            cardHolderName, 
            cardType, 
            java.sql.Date.valueOf(expirationDate), 
            cvv,
            user.getUserId(), 
            new java.sql.Timestamp(java.sql.Date.valueOf(expirationDate).getTime()), 
            false
        );
        double amountPaid = db.processReservationAsComplete(user, paymentMethod);
        // redirect to the cart page
        req.setAttribute("amtPaid", amountPaid);
        String cardNumberPadded = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        req.setAttribute("card", cardNumberPadded);
        req.setAttribute("cardholderName", cardHolderName);
        req.setAttribute("cardType", cardType);
        RequestDispatcher dispatcher = req.getRequestDispatcher("transactionReceipt.jsp");
        dispatcher.forward(req, resp);
        // resp.sendRedirect("transactionReceipt.jsp");
    }
}
