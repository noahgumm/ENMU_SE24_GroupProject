package com.hotelreservationapp.controllers;

import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminHomeTests {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private DatabaseManager databaseManager;

    @InjectMocks
    private AdminHomeController servlet;

    @Before
    public void setUp() {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGetAdminHome() throws Exception {
        when(request.getServletPath()).thenReturn("/AdminHome");

        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
        verify(requestDispatcher, never()).include(request, response);
    }

    @Test
    public void testDoGetAddAdmin() throws Exception {
        when(request.getServletPath()).thenReturn("/AddAdmin");

        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
        verify(requestDispatcher, never()).include(request, response);
    }

    @Test
    public void testDoPostAddAdmin() throws Exception {
        when(request.getServletPath()).thenReturn("/AddAdmin");
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password");

        servlet.doPost(request, response);

        verify(databaseManager).adminDbManager.createAdminUser("testUser", "password", "test@example.com");
        verify(request).setAttribute("message", "Admin account created");
        verify(requestDispatcher).forward(request, response);
        verify(requestDispatcher, never()).include(request, response);
    }

    // Add more tests for doPost() for other cases

}