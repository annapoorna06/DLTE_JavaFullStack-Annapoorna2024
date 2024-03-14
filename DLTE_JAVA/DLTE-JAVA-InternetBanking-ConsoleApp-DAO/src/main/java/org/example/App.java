package org.example;

import org.example.Middleware.DatabaseTarget;
import org.example.Middleware.UserDetailsDatabaseRepository;
import org.example.Remote.StorageTarget;
import org.example.Services.UserDetailsServices;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StorageTarget storageTarget=new DatabaseTarget();
        UserDetailsServices userDetailsServices=new UserDetailsServices(storageTarget);
      //DatabaseTarget databaseTarget=new DatabaseTarget();
       // UserDetailsDatabaseRepository userDetailsDatabaseRepository=new UserDetailsDatabaseRepository();
        //System.out.println(userDetailsServices.callFindAllByUsers("annapoornapai"));
    }
}
