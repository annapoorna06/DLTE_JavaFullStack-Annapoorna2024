package TransactionModule;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/transaction/*")
public class WebService extends HttpServlet {
    public ArrayList<Transaction> allTransaction = (ArrayList<Transaction>) Stream.of(
            new Transaction(new Date("1/20/2024"), 5000, "Annapoorna", "Friend"),
            new Transaction(new Date("6/10/2024"), 100, "Arundhathi", "Friend"),
            new Transaction(new Date("7/15/2023"), 65, "Akshira", "Education"),
            new Transaction(new Date("8/7/2022"), 70, "Divija", "Bills"),
            new Transaction(new Date("1/20/2024"), 500000, "Akshatha", "Family")
    ).collect(Collectors.toList());

    //doget and doPut methods overridden

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMaxAmount = req.getParameter("Maximum");
        String requestMinAmount = req.getParameter("Minimum");
        if (requestMaxAmount != null && requestMinAmount != null) {
            int max = Integer.parseInt(requestMaxAmount);
            int min = Integer.parseInt(requestMinAmount);
            Gson gson = new Gson();
            resp.setContentType("application/json");
            for (Transaction each : allTransaction) {
                //range of max and min transaction details
                if (each.getAmountInTransaction() > min && each.getAmountInTransaction() < max) {
                    resp.getWriter().println(gson.toJson(each));
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            Gson gson = new Gson();
            resp.setContentType("application/json");
            String json = gson.toJson(allTransaction);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Transaction transaction = gson.fromJson(req.getReader(), Transaction.class);
        allTransaction.add(transaction);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("to" + transaction.getToWhom() + "transaction has been done");
    }
}
