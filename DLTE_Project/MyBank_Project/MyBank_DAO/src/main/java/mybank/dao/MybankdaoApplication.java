package mybank.dao;

import mybank.dao.services.LoanServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MybankdaoApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(MybankdaoApplication.class, args);
    }

}
