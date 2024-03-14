package org.example.Services;

import org.example.Entity.Transactions;
import org.example.Entity.UserDetails;
import org.example.Exceptions.UserDetailsException;
import org.example.Middleware.UserDetailsDatabaseRepository;
import org.example.Remote.StorageTarget;
import org.example.Remote.UserDetailsRepository;

import java.sql.Date;
import java.util.List;

public class UserDetailsServices {
    UserDetailsRepository userDetailsRepository;
    StorageTarget storageTarget;
    public UserDetailsServices(StorageTarget storageTarget) {
        userDetailsRepository = storageTarget.getUserDetailsRepository();
    }
    //    public UserDetails getUserDetailsByUsername(String username) {
//        try {
//            List<UserDetails> userDetailsList = storageTarget.getUserDetailsRepository().getAllUserDetails();
//
//            return userDetailsList.stream()
//                    .filter(userDetails -> userDetails.getuserName().equals(username))
//                    .findFirst()
//                    .orElse(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    public void callUpdate(UserDetails userDetails) {
        try {
            userDetailsRepository.update(userDetails);
        } catch (UserDetailsException userDetailsException) {
            throw userDetailsException;
        }
    }

    public UserDetails callVerifyPassword(String username, String password) {
        try {
            return (UserDetails) userDetailsRepository.verifyPassword(username, password);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Transactions> callFindAll(){
        try{
            return userDetailsRepository.findAll();
        }
        catch(Exception e){
            return null;
        }
    }
    public List<Transactions> callFindAllByUsers(String username) {
        try {
            return userDetailsRepository.findAllUsers(username);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Transactions> callFindAllBydate(Date date, String username){
        try{
            return userDetailsRepository.findAllByDate(date,username);
        }
        catch (Exception e){
            return null;
        }
    }
}
