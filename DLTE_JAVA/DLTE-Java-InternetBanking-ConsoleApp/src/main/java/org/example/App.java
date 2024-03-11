package org.example;
import org.example.Entity.UserDetails;
import org.example.Exceptions.UserDetailsException;
import org.example.Middleware.FileStorageTarget;
import org.example.Middleware.UserDetailsFileRepository;
import org.example.Remote.StorageTarget;
import org.example.Services.UserDetailsServices;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.System.exit;

public class App {

    private static StorageTarget storageTarget;
    private static UserDetailsServices services;
    private static Scanner scanner = new Scanner(System.in);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static UserDetails userDetails;
    private static Logger logger = Logger.getLogger(UserDetailsFileRepository.class.getName());
    public static void main(String[] args) {

        int option;
        String username, password;
        storageTarget = new FileStorageTarget();
        services = new UserDetailsServices(storageTarget);
        //login menu starts
        System.out.println(resourceBundle.getString("app.login.menu"));
        option = scanner.nextInt();
        services.callAddUsers();
        if (option == 1) {
            //login validation of user
            System.out.println("Enter Your Username");
            username = scanner.next();
            System.out.println("Enter Password");
            password = scanner.next();
            userDetails=services.callVerifyPassword(username, password);
            if (userDetails!=null) {
                //if user validated moves on to the dashboard
                while (true) {
                    System.out.println(resourceBundle.getString("app.dashboard.menu"));
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            updateUserDetails();
                            break;
                        case 2: case 3: case 4: case 5:
                            System.out.println("Site under construction!!!");
                            break;
                        case 6:
                            System.out.println("Thank You for choosing our Bank");
                            exit(0);
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }//end of while(46)
            } else {
                System.out.println("Login failed. Exiting...");
            }
        }
    }//end of main(27)
    //method to read new credentials of users and update them
    private static void updateUserDetails() {
        System.out.println("Enter the details you wish to update among \npassword\n address\n email\n phone");
        String userInput = scanner.next();
        String[] properties = userInput.split(",");
        int size = properties.length;
        for (int index = 0; index < size; index++) {
            if (properties[index].equalsIgnoreCase("password")) {
                System.out.println("Enter the old password");
                scanner.nextLine();
                if (userDetails.getpassword().equals(scanner.nextLine())) {
                    System.out.println("Set new password");
                    userDetails.setpassword(scanner.nextLine());
                } else {
                    System.out.println("Password can't be set");
                    logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("update.failed"));
                    exit(0);
                }
            }
            if (properties[index].equalsIgnoreCase("address")) {
                System.out.println("Enter the new address");
                scanner.nextLine();
                userDetails.setaddress(scanner.nextLine());
            }

            if (properties[index].equalsIgnoreCase("email")) {
                System.out.println("Enter the email ");
                scanner.nextLine();
                userDetails.setemailId(scanner.nextLine());
            }
            try {
                if (properties[index].equalsIgnoreCase("phone")) {
                    System.out.println("Enter the new phone number ");
                    userDetails.setphoneNumber(scanner.nextLong());
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }//end of for(72)

        try {
            services.callUpdate(userDetails);
            logger.log(Level.INFO, userDetails.getuserName() + resourceBundle.getString("user.update.done"));
        } catch (UserDetailsException e) {
            System.out.println("Failed to update user details: " + e.getMessage());
        }
    }//end of updateUserDetails method(66)
}//end of public class App(19)