package backend.datarepo;

import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ResourceBundle;

public class ConnectionTarget {
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("Database");
    private Connection connection;
    public Connection ConnectionApp(){
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
            return connection;
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return null;
    }
    public boolean isEstablished(){
        return connection!=null;
    }
}
