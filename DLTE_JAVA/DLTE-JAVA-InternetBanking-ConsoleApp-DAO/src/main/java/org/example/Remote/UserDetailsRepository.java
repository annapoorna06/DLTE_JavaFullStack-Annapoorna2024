package org.example.Remote;

import org.example.Entity.Transactions;
import org.example.Entity.UserDetails;

import java.util.Date;
import java.util.List;

public interface UserDetailsRepository {

    //void save(UserDetails userDetails);
    //void addUsers();
    void update(UserDetails userDetails);
    Object verifyPassword(String username, String password);
    List<Transactions> findAll();
    List<Transactions> findAllUsers(String username);
    List<Transactions> findAllByDate(Date date, String username);
}
