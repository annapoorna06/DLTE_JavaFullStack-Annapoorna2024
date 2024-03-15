package example;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet("update")
public class Update extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lookup the data source
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/OracleDS");
            try (Connection conn = dataSource.getConnection()) {
                String transactionIdParam = req.getParameter("transactionId");
                String amountParam = req.getParameter("amount");

                if (transactionIdParam != null && amountParam != null) {
                    int transactionId = Integer.parseInt(transactionIdParam);
                    int amount = Integer.parseInt(amountParam);

                    String sql = "UPDATE Transactions SET transaction_amount = transaction_amount + ? WHERE transaction_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, transactionId);
                        stmt.setInt(3, amount);
                        stmt.executeUpdate();
                    }
                } else {
                    throw new ServletException("transactionId or amount parameter is null");
                }
            }
        } catch (NamingException | SQLException e) {
            throw new ServletException("Error updating account", e);
        }
    }
}
