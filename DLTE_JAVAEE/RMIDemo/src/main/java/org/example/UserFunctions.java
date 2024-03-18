package org.example;

import org.example.Entity.Transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserFunctions extends Remote {
    List<Transactions> fetchAll()throws RemoteException;
}
