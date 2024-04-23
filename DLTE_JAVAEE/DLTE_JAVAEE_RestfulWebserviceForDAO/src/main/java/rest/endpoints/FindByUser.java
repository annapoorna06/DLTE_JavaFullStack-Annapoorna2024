package rest.endpoints;
import com.google.gson.Gson;
import org.example.Entity.Transactions;
import org.example.Exceptions.UserDetailsException;
import org.example.Middleware.DatabaseTarget;
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
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@WebServlet("/rest/alluser/")
public class FindByUser extends HttpServlet {
    UserDetailsServices userDetailsServices;
    private ResourceBundle resourceBundle;
    private Logger logger;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        userDetailsServices=new UserDetailsServices(storageTarget);
        resourceBundle=ResourceBundle.getBundle("exception");
        logger= LoggerFactory.getLogger(FindByUser.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String requestname = req.getParameter("username");
        try {
            List<Transactions> transactions = userDetailsServices.callFindAllByUsers(requestname);
            Gson gson = new Gson();
            String responseData = gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(responseData);
        }
        catch (UserDetailsException | MissingResourceException userDetailsException){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println(resourceBundle.getString("user.not.found"));
        }
    }
}