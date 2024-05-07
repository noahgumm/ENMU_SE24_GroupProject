import com.hotelreservationapp.controllers.AdminLoginController;
import com.hotelreservationapp.models.Database.Tables.Admin;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminLoginTests {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private DatabaseManager databaseManager;

    @InjectMocks
    private AdminLoginController servlet;

    private List<Admin> adminList;

    @Before
    public void setUp() {
        adminList = new ArrayList<>();
        // Add test admin data
        Admin admin = new Admin();
        admin.setEmail("test@example.com");
        admin.setPassword("password123");
        admin.setUsername("testAdmin");
        adminList.add(admin);

        // Configure mock behavior
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(databaseManager.adminDbManager.getAdminAllUsers()).thenReturn(adminList);
    }

    @Test
    public void testLoginAdmin_Success() throws Exception {
        // Set up request parameters
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password123");

        // Call the method under test
        servlet.loginAdmin(request, response);

        // Verify that the session attribute is set
        verify(request.getSession()).setAttribute("username", "testAdmin");

        // Verify that the request is forwarded to the AdminHome page
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testLoginAdmin_Failure() throws Exception {
        // Set up request parameters with incorrect password
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("incorrectPassword");

        // Call the method under test
        servlet.loginAdmin(request, response);

        // Verify that the error session attribute is set
        verify(request.getSession()).setAttribute("error", "Error finding account.");

        // Verify that the response is redirected to the login page
        verify(response).sendRedirect("adminLoginView.jsp");
    }
}