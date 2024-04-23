package rest.servlet;
import org.example.Entity.UserDetails;
import org.example.Middleware.DatabaseTarget;
import org.example.Middleware.UserDetailsDatabaseRepository;
import org.example.Remote.StorageTarget;
import org.example.Services.UserDetailsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.endpoints.UpdateAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

@WebServlet("/update")
public class NewAccountServlet extends HttpServlet {
    UserDetailsServices userDetailsServices;
    public ResourceBundle resourceBundle;
    public Logger logger;
    public UserDetailsDatabaseRepository repository;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userDetailsServices = new UserDetailsServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(UpdateAccount.class);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New account servlet invoked");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String dateOfBirth=req.getParameter("dateOfBirth");
        String address=req.getParameter("address");
        String emailId = req.getParameter("email");
        long phoneNumber=Long.parseLong( req.getParameter("phoneNumber"));
        UserDetails userDetails= new UserDetails(username, password, dateOfBirth, address, emailId, phoneNumber);

        req.setAttribute("info", resourceBundle.getString("account.created"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("newaccount.jsp");
        dispatcher.forward(req, resp);
    }
}

