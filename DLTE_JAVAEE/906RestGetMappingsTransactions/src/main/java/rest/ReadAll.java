package rest;

import com.google.gson.Gson;
import org.example.Entity.Transactions;
import org.example.Middleware.DatabaseTarget;
import org.example.Remote.StorageTarget;
import org.example.Services.UserDetailsServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rest/all/*")
public class ReadAll extends HttpServlet {
    UserDetailsServices userDetailsServices;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        userDetailsServices=new UserDetailsServices(storageTarget);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Transactions> transactions=userDetailsServices.callFindAll();
        System.out.println(transactions);
        Gson gson=new Gson();
        String responseData = gson.toJson(transactions);
        resp.getWriter().println(responseData);
    }


}
