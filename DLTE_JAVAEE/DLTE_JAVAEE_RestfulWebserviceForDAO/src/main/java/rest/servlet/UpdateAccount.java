package rest.servlet;


import org.example.Entity.UserDetails;
import org.example.Middleware.DatabaseTarget;
import org.example.Middleware.UserDetailsDatabaseRepository;
import org.example.Remote.StorageTarget;
import org.example.Services.UserDetailsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

@WebServlet("/update")
public class UpdateAccount extends HttpServlet {
    UserDetailsServices userDetailsServices;
    public ResourceBundle resourceBundle;
    public Logger logger;
    public UserDetailsDatabaseRepository repository;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userDetailsServices = new UserDetailsServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(rest.endpoints.UpdateAccount.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Approve servlet invoked");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String dateOfBirth=req.getParameter("dateOfBirth");
        String address=req.getParameter("address");
        String emailId = req.getParameter("email");
        long phoneNumber=Long.parseLong( req.getParameter("phoneNumber"));
        UserDetails userDetails= new UserDetails(username, password, dateOfBirth, address, emailId, phoneNumber);
        userDetailsServices.callUpdate(userDetails);
        resp.sendRedirect("viewUser.jsp");
    }
}
