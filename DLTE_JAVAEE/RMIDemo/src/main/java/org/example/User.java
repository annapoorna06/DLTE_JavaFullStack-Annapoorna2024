package org.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Hashtable;

public class User implements Serializable {
    public static void main(String[] args) throws NamingException, RemoteException {
        Hashtable properties=new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        properties.put(Context.PROVIDER_URL,"rmi://localhost:1010");
        Context context=new InitialContext(properties);
        UserFunctions userFunctions = (UserFunctions) context.lookup("java:/MyUser");
        userFunctions.fetchAll().forEach(System.out::println);
    }
}
