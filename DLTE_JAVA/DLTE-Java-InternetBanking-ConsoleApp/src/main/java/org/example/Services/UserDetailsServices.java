package org.example.Services;

import org.example.Entity.UserDetails;
import org.example.Exceptions.UserDetailsException;
import org.example.Remote.StorageTarget;
import org.example.Remote.UserDetailsRepository;

import java.util.ArrayList;

public class UserDetailsServices {
    UserDetailsRepository userDetailsRepository;

    public UserDetailsServices(StorageTarget storageTarget) {
        userDetailsRepository = storageTarget.getUserDetailsRepository();
    }

//    public void callAddUsers() {
//        try {
//            userDetailsRepository.addUsers();
//        } catch (Exception e) {
//        }
//    }

//    public void callSave(UserDetails userDetails) {
//        try {
//            userDetailsRepository.save(userDetails);
//        } catch (UserDetailsException userDetailsException) {
//            throw userDetailsException;
//        }
//    }

    public void callUpdate(UserDetails userDetails) {
        try {
            userDetailsRepository.update(userDetails);
        } catch (UserDetailsException userDetailsException) {
            throw userDetailsException;
        }
    }

    public boolean callVerifyPassword(String username, String password) {
        try {
            return userDetailsRepository.verifyPassword(username, password);
        } catch (Exception e) {
            return false;
        }
    }
}
