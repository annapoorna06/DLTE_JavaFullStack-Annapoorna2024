//package org.example;
//
//import org.example.Entity.UserDetails;
//import org.example.Middleware.FileStorageTarget;
//import org.example.Remote.StorageTarget;
//import org.example.Services.UserDetailsServices;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.Scanner;
//
//public class App {
//
//    private static StorageTarget storageTarget;
//    private static UserDetailsServices services;
//    private static Scanner scanner = new Scanner(System.in);
//    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application") ;
//    private static UserDetails userDetails;
//    public static void main(String[] args) {
//        int option=0;
//        String username,password;
//        storageTarget=new FileStorageTarget();
//        //storageTarget=new DatabaseTarget();
//        services=new UserDetailsServices(storageTarget);
//        //services.callAddTransactions();
//        //System.out.println( services.callFinaAll().toString());
//
//        System.out.println(resourceBundle.getString("app.login.menu"));
//        option = scanner.nextInt();
//        //  List<UserDetails> detailsList=new ArrayList<>();
//        if(option==1){
//            System.out.println("Enter Your Username");
//            username = scanner.next();
//            System.out.println("Enter Password");
//            password = scanner.next();
//            if(services.callVerifyPassword(username,password))
//                while (true){
//                    System.out.println(resourceBundle.getString("app.dashboard.menu"));
//                    option = scanner.nextInt();
//                    switch (option) {
//                        case 1:
//                            System.out.println("Enter the details you wish update among (password,address,email,phone)");
//                            String userInput= scanner.next();// available,pin
//                            String[] properties=userInput.split(",");
//                            int size= properties.length;
//                            for(int index=0;index<size;index++){
//                                if(properties[index].equalsIgnoreCase("phone")){
//                                    System.out.println("Enter the new phone number ");
//                                    userDetails.setphoneNumber(scanner.nextLong());
//                                }
//                                if(properties[index].equalsIgnoreCase("address")){
//                                    System.out.println("enter the new address");
//                                    userDetails.setaddress(scanner.nextLine());
//                                }
//                                if(properties[index].equalsIgnoreCase("email")){
//                                    System.out.println("enter the email ");
//                                    userDetails.setemailId(scanner.nextLine());
//                                }
//                                if(properties[index].equalsIgnoreCase("password")){
//                                    System.out.println("Enter the old password");
//                                    if(userDetails.getpassword().equals(scanner.nextLine())){
//                                        System.out.println("Set new password");
//                                        userDetails.setpassword(scanner.nextLine());
//                                    }
//                                    else{
//                                        System.out.println("Password can't be set");
//                                    }
//                                }
//                            }
//                            services.callUpdate(userDetails);
//                            break;
//                        case 2:
//                        case 3:
//                        case 4:
//                        case 5:
//                        case 6:
//                        default:
//                            System.out.println("Thank You");
//                            System.exit(0);
//                    }
//                }
//        }
//        else{
//            System.exit(0);
//        }
//
//    }
//}
package org.example;

import org.example.Entity.UserDetails;
import org.example.Exceptions.UserDetailsException;
import org.example.Middleware.FileStorageTarget;
import org.example.Remote.StorageTarget;
import org.example.Services.UserDetailsServices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App implements Serializable {

    private static StorageTarget storageTarget;
    private static UserDetailsServices services;
    private static Scanner scanner = new Scanner(System.in);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static UserDetails userDetails;

    public static void main(String[] args) {
        int option = 0;
        String username, password;
        storageTarget = new FileStorageTarget();
        //storageTarget=new DatabaseTarget();
        services = new UserDetailsServices(storageTarget);
        //services.callAddTransactions();
        //System.out.println( services.callFinaAll().toString());

        System.out.println(resourceBundle.getString("app.login.menu"));
        option = scanner.nextInt();
         List<UserDetails> detailsList=new ArrayList<>();
        if (option == 1) {
            System.out.println("Enter Your Username");
            username = scanner.next();
            System.out.println("Enter Password");
            password = scanner.next();
            if (services.callVerifyPassword(username, password)) {
                while (true) {
                    System.out.println(resourceBundle.getString("app.dashboard.menu"));
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            System.out.println("Enter the details you wish to update among (password, address, email, phone)");
                            String userInput = scanner.next();// available,pin
                            String[] properties = userInput.split(",");
                            int size = properties.length;
//                            if (userDetails == null) {
//                                userDetails = new UserDetails();
//                            }
                            for (int index = 0; index < size; index++) {
                                try {
                                    if (properties[index].equalsIgnoreCase("phone")) {
                                        System.out.println("Enter the new phone number ");
                                        userDetails.setphoneNumber(scanner.nextLong());
                                    }
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                }
                                if (properties[index].equalsIgnoreCase("address")) {
                                    System.out.println("Enter the new address");
                                    userDetails.setaddress(scanner.nextLine());
                                }
                                if (properties[index].equalsIgnoreCase("email")) {
                                    System.out.println("Enter the email ");
                                    userDetails.setemailId(scanner.nextLine());
                                }
                                if (properties[index].equalsIgnoreCase("password")) {
                                    System.out.println("Enter the old password");
                                    if (userDetails.getpassword().equals(scanner.nextLine())) {
                                        System.out.println("Set new password");
                                        userDetails.setpassword(scanner.nextLine());
                                    } else {
                                        System.out.println("Password can't be set");
                                    }
                                }
                            }try {
                            services.callUpdate(userDetails);
                        } catch (UserDetailsException e) {
                            System.out.println((e));
                        }
                            break;
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        default:
                            System.out.println("Thank You");
                            System.exit(0);
                    }
                }
            }
        } else {
            System.exit(0);
        }
    }
}
