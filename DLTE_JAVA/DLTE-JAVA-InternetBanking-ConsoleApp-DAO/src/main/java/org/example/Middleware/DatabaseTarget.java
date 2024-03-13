package org.example.Middleware;
import oracle.jdbc.OracleDriver;
import org.example.Remote.StorageTarget;
import org.example.Remote.UserDetailsRepository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseTarget implements StorageTarget {
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    private Connection connection;
    public DatabaseTarget(){
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
    }
   public boolean isEstablished(){
        return connection!=null;
   }
    @Override
    public UserDetailsRepository getUserDetailsRepository() {
        return new UserDetailsDatabaseRepository(connection);
    }
}
