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
    ArrayList<Integer> newList=(ArrayList<Integer>) Stream.of(new Integer(1),
            new Integer(2)
    ).collect(Collectors.toList());
    public ArrayList<Transaction> allTransaction = (ArrayList<Transaction>) Stream.of(
            new Transaction(new Date("1/20/2024"), 5000, "Annapoorna", "Friend",newList),
            new Transaction(new Date("6/10/2024"), 100, "Arundhathi", "Friend",newList),
            new Transaction(new Date("7/15/2023"), 65, "Akshira", "Education",newList),
            new Transaction(new Date("8/7/2022"), 70, "Divija", "Bills",newList),
            new Transaction(new Date("1/20/2024"), 500000, "Akshatha", "Family",newList)
    ).collect(Collectors.toList());

    //doGet and doPut methods overridden

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
//package TransactionModule;
//
//import TransactionModule.Transaction;
//import com.google.gson.Gson;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
////http://localhost:7001/DLTE-JavaEE-1/
//@WebServlet("/test")
//public class WebService extends HttpServlet {
//    // Sample transaction data
//    public ArrayList<Transaction> transactionList = (ArrayList<Transaction>) Stream.of(
//            new Transaction(new Date("1/20/2024"), 5000, "Annapoorna", "Friend"),
//            new Transaction(new Date("6/10/2024"), 100, "Arundhathi", "Friend"),
//            new Transaction(new Date("7/15/2023"), 65, "Akshira", "Education"),
//            new Transaction(new Date("8/7/2022"), 70, "Divija", "Bills"),
//            new Transaction(new Date("1/20/2024"), 500000, "Akshatha", "Family")
//    ).collect(Collectors.toList());
//
//protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//        throws IOException {
//    resp.setContentType("text/html");
//    resp.setCharacterEncoding("UTF-8");
//    PrintWriter writer = resp.getWriter();
//    writer.println("<!DOCTYPE html>");
//    writer.println("<html>");
//    writer.println("<head>");
//    writer.println("<title>Transactions</title>");
//    writer.println("<style>");
//    writer.println("ul { list-style-type: none; padding: 0; }");
//    writer.println("li { margin-bottom: 20px; padding: 10px; border: 1px solid #ccc; border-radius: 5px; }");
//    writer.println("</style>");
//    writer.println("</head>");
//    writer.println("<body>");
//    writer.println("<h1>Transactions</h1>");
//    writer.println("<ul>");
//    for (Transaction transaction : transactionList) {
//        writer.println("<li>");
//        writer.println("<strong>Date:</strong> " + transaction.getDateOfTransaction() + "<br>");
//        writer.println("<strong>Amount:</strong> " + transaction.getAmountInTransaction() + "<br>");
//        writer.println("<strong>Name:</strong> " + transaction.getToWhom() + "<br>");
//        writer.println("<strong>Type:</strong> " + transaction.getRemarks());
//        writer.println("</li>");
//    }
//    writer.println("</ul>");
//    writer.println("</body>");
//    writer.println("</html>");
//
//    // Set status to OK
//    resp.setStatus(HttpServletResponse.SC_OK);
//}
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Gson gson=new Gson();
//
//        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
//        transactionList.add(transaction);
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter writer = resp.getWriter();
//        writer.println("<!DOCTYPE html>");
//        writer.println("<html>");
//        writer.println("<head>");
//        writer.println("<title>Transaction Insertion</title>");
//        writer.println("</head>");
//        writer.println("<body>");
//        writer.println("<h1>Transaction Added</h1>");
//        writer.println("<p>" + transaction.getToWhom()+ " has been added to the records.</p>");
//        writer.println("</body>");
//        writer.println("</html>");
//
//        // Set status to OK
//        resp.setStatus(HttpServletResponse.SC_OK);
//    }
//}
