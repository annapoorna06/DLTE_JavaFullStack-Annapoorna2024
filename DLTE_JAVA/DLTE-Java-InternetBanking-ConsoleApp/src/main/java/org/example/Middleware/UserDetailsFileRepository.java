package org.example.Middleware;

import org.example.Entity.UserDetails;
import org.example.Exceptions.UserDetailsException;
import org.example.Remote.UserDetailsRepository;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserDetailsFileRepository implements UserDetailsRepository {
    private String filePath;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("userdetails");
    private Logger logger = Logger.getLogger(UserDetailsFileRepository.class.getName());
    private List<UserDetails> userDetailsList = new ArrayList<>();

    public UserDetailsFileRepository(String url) {
        filePath = url;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                // If the file doesn't exist, create a new file
            }

            FileHandler fileHandler = new FileHandler("User-details-logs.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void writeIntoFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(userDetailsList);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ioException) {
        }
    }

    public void readFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            userDetailsList = (List<UserDetails>) objectInputStream.readObject();
            //System.out.println(filePath);
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException ioException) {
        }
    }


    @Override
    public void addUsers() {
        readFromFile();
        userDetailsList.add(new UserDetails("annapoorna", "anna@123", new Date(2002 , 7 , 6), "karkala", "annapoorna@gmail.com", 9876543210L));
        userDetailsList.add(new UserDetails("sinchana", "sinchana@123", new Date(2002 ,8 ,05), "mulki", "sinchanaa@gmail.com", 9876547680L));
        userDetailsList.add(new UserDetails("shreya", "shreya@123", new Date(2002 ,11 ,12), "moodbidri", "shreya@gmail.com", 9876512345L));
        userDetailsList.add(new UserDetails("akshatha", "aksh@123", new Date(2002 ,11 , 20), "karkala", "akshatha@gmail.com", 9765443210L));
        writeIntoFile();

    }

    @Override
    public void save(UserDetails userDetails) {
        readFromFile();
        UserDetails object = userDetailsList.stream().filter(each -> each.getuserName().equals(userDetails.getuserName())).findFirst().orElse(null);
        if (object != null) {
            logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("user.exists"));
            throw new UserDetailsException();
        }
        userDetailsList.add(userDetails);
        writeIntoFile();
        logger.log(Level.INFO, userDetails.getuserName() + resourceBundle.getString("user.saved"));
        System.out.println(userDetails.getuserName() + resourceBundle.getString("user.saved"));
    }

//    @Override
//    public void update(UserDetails userDetails) {
//        readFromFile();
//        UserDetails matched = userDetailsList.stream().filter(each -> each.getuserName().equals(userDetails.getuserName())).findFirst().orElse(null);
//        if (matched == null) {
//            logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("user.notExists"));
//            throw new UserDetailsException(resourceBundle.getString("user.noMatches"));
//        }
//        int index=userDetailsList.indexOf(matched);
//        userDetailsList.set(index,userDetails);
//        writeIntoFile();
//        logger.log(Level.FINE, resourceBundle.getString("user.update.ok"));
//        System.out.println(resourceBundle.getString("user.update.ok"));
//    }
//@Override
//public void update(UserDetails userDetails) {
//    readFromFile();
//    UserDetails matched = userDetailsList.stream().filter(each -> each.getuserName().equals(userDetails.getuserName())).findFirst().orElse(null);
//    if (matched == null) {
//        logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("user.notExists"));
//        throw new UserDetailsException(resourceBundle.getString("user.noMatches"));
//    }
//
//    if (userDetailsList == null) {
//        userDetailsList = new ArrayList<>(); // Initialize userDetailsList if not set
//    }
//
//    int index = userDetailsList.indexOf(matched);
//    userDetailsList.set(index, userDetails);
//    writeIntoFile();
//
//    logger.log(Level.FINE, resourceBundle.getString("user.update.ok"));
//    System.out.println(resourceBundle.getString("user.update.ok"));
//}
@Override
public void update(UserDetails userDetails) {
    readFromFile();

    if (userDetailsList== null) {
        UserDetails matched = userDetailsList.stream()
                .filter(each -> each.getuserName().equals(userDetails.getuserName()))
                .findFirst().orElse(null);

        if (matched == null) {
            logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("user.notExists"));
            throw new UserDetailsException(resourceBundle.getString("user.noMatches"));
        }

        int index = userDetailsList.indexOf(matched);
        userDetailsList.set(index, userDetails);
        writeIntoFile();

        logger.log(Level.FINE, resourceBundle.getString("user.update.ok"));
        System.out.println(resourceBundle.getString("user.update.ok"));
    } else {
        logger.log(Level.WARNING,resourceBundle.getString( "user.null"));
        throw new UserDetailsException(resourceBundle.getString("user.null"));
    }
}



    @Override
    public boolean verifyPassword(String username, String password) {

        readFromFile();

        UserDetails account = userDetailsList.stream().filter(each -> each.getuserName().equals(username)).findFirst().orElse(null);
        if (account == null) {
            System.out.println("Username not found");
            return false;
        } else if (!account.getpassword().equals(password)) {
            System.out.println("Password is Incorrect");
            return false;
        } else
            return true;
    }
}
