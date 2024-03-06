package org.example.Remote;

import org.example.Entity.UserDetails;

import java.util.List;

public interface UserDetailsRepository {

    void save(UserDetails userDetails);
    void addUsers();
    void update(UserDetails userDetails);
    boolean verifyPassword(String username, String password);
}
