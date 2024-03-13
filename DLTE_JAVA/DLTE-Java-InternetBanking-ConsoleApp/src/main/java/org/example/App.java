package org.example;

import org.example.Entity.UserDetails;
import org.example.Exceptions.UserDetailsException;
import org.example.Middleware.DatabaseTarget;
import org.example.Middleware.UserDetailsFileRepository;
import org.example.Remote.StorageTarget;
import org.example.Services.UserDetailsServices;

import java.util.Scanner;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.System.exit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.InputMismatchException;

public class App {

    private static UserDetails userDetails;
    private static Logger logger = Logger.getLogger(UserDetailsFileRepository.class.getName());

    private static StorageTarget storageTarget;
    private static UserDetailsServices services;
    private static Scanner scanner = new Scanner(System.in);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {

        int option;
        String username, password;
        storageTarget = new DatabaseTarget();
        services = new UserDetailsServices(storageTarget);

        System.out.println(resourceBundle.getString("app.login.menu"));
        option = scanner.nextInt();

        if (option == 1) {
            System.out.println("Enter Your Username");
            username = scanner.next();
            System.out.println("Enter Password");
            password = scanner.next();
            userDetails = services.callVerifyPassword(username, password);
            if (userDetails != null) {
                while (true) {
                    System.out.println(resourceBundle.getString("app.dashboard.menu"));
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            updateUserDetails();
                            break;
                        case 2:
                            System.out.println("Your details are:");
                            displayUserDetails();
                            break;
                        case 3: case 4: case 5:
                            System.out.println("Site under construction!!!");
                            break;
                        case 6:
                            System.out.println("Thank You for choosing our Bank");
                            exit(0);
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            } else {
                System.out.println("Login failed. Exiting...");
            }
        }
    }

    private static void displayUserDetails() {
        System.out.println("Username:"+userDetails.getuserName()+"\nDate of Birth:"+userDetails.getdateOfBirth()+"\nPhone Number:"+userDetails.getphoneNumber()+"\nAddress:"+userDetails.getaddress()
                +"\nEmail Id:"+userDetails.getemailId());
    }

    private static void updateUserDetails() {
        System.out.println("Enter the details you wish to update among \npassword\n address\n email\n phone");
        String userInput = scanner.next();
        String[] properties = userInput.split(",");
        int size = properties.length;

        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Pattern phonePattern = Pattern.compile("\\d{10}");

        for (int index = 0; index < size; index++) {
            if (properties[index].equalsIgnoreCase("password")) {
                System.out.println("Enter the old password");
                scanner.nextLine();
                if (userDetails.getpassword().equals(scanner.nextLine())) {
                    System.out.println("Set new password");
                    String newPass = scanner.nextLine();
                    Matcher matcher = passwordPattern.matcher(newPass);
                    if (matcher.matches()) {
                        userDetails.setpassword(newPass);
                    } else {
                        System.out.println("Password must contain at least 8 characters, including uppercase, lowercase, numbers, and special characters.");
                        logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("update.failed"));
                        exit(0);
                    }
                    break;
                } else {
                    System.out.println("Password can't be set");
                    logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("update.failed"));
                    exit(0);
                }
            }

            if (properties[index].equalsIgnoreCase("address")) {
                System.out.println("Enter the password");
                scanner.nextLine();
                if (userDetails.getpassword().equals(scanner.nextLine())) {
                    System.out.println("Enter the new address");
                    userDetails.setaddress(scanner.nextLine());
                    break;
                } else {
                    System.out.println(resourceBundle.getString("wrong.password"));
                    updateUserDetails();
                }
                break;
            }

            if (properties[index].equalsIgnoreCase("email")) {
                System.out.println("Enter the password");
                if (userDetails.getpassword().equals(scanner.next())) {
                    System.out.println("Enter the new email ");
                    scanner.nextLine();
                    String newEmail = scanner.nextLine();
                    Matcher matcher = emailPattern.matcher(newEmail);
                    if (matcher.matches()) {
                        userDetails.setemailId(newEmail);
                    } else {
                        System.out.println("Invalid email format. Please enter a valid email address.");
                        updateUserDetails();
                    }
                } else {
                    System.out.println(resourceBundle.getString("wrong.password"));
                    updateUserDetails();
                }
                break;
            }


            if (properties[index].equalsIgnoreCase("phone")) {
                System.out.println("Enter the password");
                scanner.nextLine();
                if (userDetails.getpassword().equals(scanner.nextLine())) {
                    System.out.println("Enter the new phone number ");
                    long newPhone = scanner.nextLong();
                    Matcher matcher = phonePattern.matcher(String.valueOf(newPhone)); // Converted newPhone to String for matcher
                    if (matcher.matches()) {
                        userDetails.setphoneNumber(newPhone);
                    } else {
                        System.out.println("Invalid phone number format. Please enter a 10-digit phone number.");
                        updateUserDetails();
                    }
                } else {
                    System.out.println(resourceBundle.getString("wrong.password"));
                    updateUserDetails();
                }
            }
        }

        try {
            services.callUpdate(userDetails);
            logger.log(Level.INFO, userDetails.getuserName() + resourceBundle.getString("user.update.done"));
        } catch (UserDetailsException e) {
            System.out.println("Failed to update user details: " + e.getMessage());
        }
    }
}
