package service;
import service.EmployeeWebServiceImplementation;
import javax.xml.ws.Endpoint;

public class EndPoint {
        public static void main(String[] args) {
            String url = "http://localhost:1005/employee";
            Endpoint.publish(url, new service.EmployeeWebServiceImplementation());
            System.out.println("Employee Web Service is published at: " + url);
        }
    }

