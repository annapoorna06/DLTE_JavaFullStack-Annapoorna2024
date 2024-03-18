package org.example;

import org.example.Entity.Transactions;
import org.example.Entity.UserDetails;
import org.example.Middleware.DatabaseTarget;
import org.example.Services.UserDetailsServices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class UserServer extends UnicastRemoteObject implements UserFunctions, Serializable {
    private static Context context;
    private Registry registry;
    private UserDetailsServices services;
    @Override
    public List<Transactions> fetchAll() throws RemoteException {
        List<Transactions> transactions = services.callFindAll();
        List<Transactions> returned=new ArrayList<>();
        for(Transactions transactions1:transactions){
            returned.add(transactions1);
        }
        return returned;
    }
    public UserServer() throws RemoteException {
        super();
        services=new UserDetailsServices(new DatabaseTarget());
        try {
            registry= LocateRegistry.createRegistry(1010);
            Hashtable properties=new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
            properties.put(Context.PROVIDER_URL,"rmi://localhost:1010");
            context=new InitialContext(properties);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws NamingException, RemoteException {
        UserServer userServer=new UserServer();
        context.bind("java:/MyUser",userServer);
    }
}
