package org.example.Remote;

import org.example.Entity.UserDetails;

import java.util.List;

public interface UserDetailsRepository {

    void save(UserDetails userDetails);
    void addUsers();
    void update(UserDetails userDetails);
    Object verifyPassword(String username, String password);
   // UserDetails getUserDetailsByUsername(String username);

    List<UserDetails> getAllUserDetails();
}
