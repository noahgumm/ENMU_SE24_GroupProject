package com.hotelreservationapp.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.Database.Tables.User;

public class BaseController extends HttpServlet {
    public BaseController() {
        super();
    }

    public User getSessionUserOrSendToLoginPage(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if(user == null) {
            try{
                resp.sendRedirect("login.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return user;
    }

    public User getSessionUser(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("user");
    }

    /**
     * Set the session user. Should be used on login.
     * @param req
     * @param user
     */
    public void setSessionUser(HttpServletRequest req, User user) {
        //set session user. Set the session timeout to 10 minutes.
        req.getSession().setAttribute("user", user);
        req.getSession().setMaxInactiveInterval(10*60);
    }

    /**
     * Logs out the session user.
     * @param req
     */
    public void removeSessionUser(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
    }
}
