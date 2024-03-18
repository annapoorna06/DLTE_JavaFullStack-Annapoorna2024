package rest;

import com.google.gson.Gson;
import org.example.Entity.UserDetails;
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
import java.util.ResourceBundle;

@WebServlet("/update/")
public class UpdateAccount extends HttpServlet {
    UserDetailsServices userDetailsServices;
    private ResourceBundle resourceBundle;
    private Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userDetailsServices = new UserDetailsServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(UpdateAccount.class);
    }
<<<<<<< HEAD
//        @Override
//        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            resp.setContentType("application/json");
//            try{
//                Gson gson=new Gson();
//                UserDetails userDetails = gson.fromJson(req.getReader(), UserDetails.class);
//                UserDetailsServices.calladdusers(userDetails);
//                resp.setStatus(HttpServletResponse.SC_OK);
//                resp.getWriter().println(resourceBundle.getString("user.saved"));
//            }
//            catch(NumberFormatException numberFormatException){
//                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                resp.getWriter().println(numberFormatException);
//            }
//            catch (UserDetailsException userDetailsException){
//                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                resp.getWriter().println(resourceBundle.getString("user.not.found"));
//            }
//        }

=======
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("application/json");
            try{
                Gson gson=new Gson();
                UserDetails userDetails = gson.fromJson(req.getReader(), UserDetails.class);
                UserDetailsServices.calladdusers(userDetails);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println(resourceBundle.getString("user.saved"));
            }
            catch(NumberFormatException numberFormatException){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().println(numberFormatException);
            }
            catch (UserDetailsException userDetailsException){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().println(resourceBundle.getString("user.not.found"));
            }
        }
    
>>>>>>> 1cfdd82c1af8e185458f90860ea3c1177360e60d

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            Gson gson = new Gson();
            UserDetails userDetails = gson.fromJson(req.getReader(), UserDetails.class);
            userDetailsServices.callUpdate(userDetails);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(resourceBundle.getString("user.updated"));
        } catch (NumberFormatException numberFormatException) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(numberFormatException);
        } catch (UserDetailsException userDetailsException) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(resourceBundle.getString("user.not.found"));
        }
    }
}




