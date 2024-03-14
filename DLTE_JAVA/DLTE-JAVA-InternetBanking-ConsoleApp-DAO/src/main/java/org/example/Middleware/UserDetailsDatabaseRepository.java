package org.example.Middleware;

import org.example.Entity.Transactions;
import org.example.Entity.UserDetails;
import org.example.Exceptions.UserDetailsException;
import org.example.Remote.UserDetailsRepository;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserDetailsDatabaseRepository implements UserDetailsRepository {
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("userdetails");
    private Logger logger = Logger.getLogger(UserDetailsDatabaseRepository.class.getName());
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public UserDetailsDatabaseRepository(Connection connection) {
        try{
            this.connection=connection;
            FileHandler fileHandler=new FileHandler("User-details-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }
        catch (IOException ioException){
            System.out.println(ioException);
        }
    }

//    @Override
//    public void addUsers() {
//        try {
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO UserDetails(username, password, dob, address, email, phone) VALUES (?, ?, ?, ?, ?, ?)");
////            statement.setString(1, "annapoornapai");
////            statement.setString(2, "anna");
////            statement.setDate(3, new java.sql.Date(new Date(2002, 7, 6).getTime()));
////            statement.setString(4, "karkala");
////            statement.setString(5, "annapoorna@gmail.com");
////            statement.setLong(6, 9876543210L);
////            statement.executeUpdate();
////            statement.close();
//            logger.log(Level.INFO, "Users added successfully to the database.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void update(UserDetails userDetails) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE UserDetails SET password=?, dob=?, address=?, email=?, phone=? WHERE username=?");
            statement.setString(1, userDetails.getpassword());
            statement.setDate(2, new java.sql.Date(userDetails.getdateOfBirth().getTime()));
            statement.setString(3, userDetails.getaddress());
            statement.setString(4, userDetails.getemailId());
            statement.setLong(5, userDetails.getphoneNumber());
            statement.setString(6, userDetails.getuserName());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                logger.log(Level.WARNING, userDetails.getuserName() + " does not exist in the database.");
                throw new UserDetailsException("User does not exist.");
            } else {
                logger.log(Level.INFO, "Credential updated for " + userDetails.getuserName());
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Object verifyPassword(String username, String password) {
        int attempts = 3; // Number of attempts allowed
        Scanner scanner = new Scanner(System.in);

        while (attempts > 0) {
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserDetails WHERE username=?");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    if (password.equals(storedPassword)) {
                        UserDetails userDetails = new UserDetails(
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getDate("dob"),
                                resultSet.getString("address"),
                                resultSet.getString("email"),
                                resultSet.getLong("phone")
                        );
                        System.out.println(resourceBundle.getString("login.success"));
                        return userDetails;
                    } else {
                        attempts--;
                        if (attempts == 0) {
                            logger.log(Level.WARNING, "username.locked");
                            throw new UserDetailsException("Too many failed attempts. Account locked.");
                        }
                        System.out.println("Incorrect password. Attempts left: " + attempts);
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();
                    }
                } else {
                    logger.log(Level.WARNING, "username.not.found");
                    throw new UserDetailsException("Username not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
        return null;
    }


    @Override
    public List<Transactions> findAll() {
        ArrayList<Transactions> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions";
            preparedStatement=connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Date date=resultSet.getDate(2);
                if(date!=null)
                transactionArrayList.add(new Transactions(date,resultSet.getLong(1),resultSet.getString(5),resultSet.getDouble(3),resultSet.getDouble(4)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }

    @Override
    public List<Transactions> findAllUsers(String username) {
        ArrayList<Transactions> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions where username=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                transactionArrayList.add(new Transactions(resultSet.getDate(2),resultSet.getLong(1),resultSet.getString(5),resultSet.getDouble(3),resultSet.getDouble(4)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }


    @Override
    public List<Transactions> findAllByDate(Date date, String username) {
        ArrayList<Transactions> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions where username=? and transaction_date=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setDate(2, (java.sql.Date) date);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                transactionArrayList.add(new Transactions(resultSet.getDate(2),resultSet.getLong(1),resultSet.getString(5),resultSet.getDouble(3),resultSet.getDouble(4)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }

}
