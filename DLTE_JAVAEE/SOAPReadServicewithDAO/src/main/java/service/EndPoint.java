package service;

import org.example.Entity.Transactions;

import javax.xml.ws.Endpoint;

public class EndPoint {
    private static String url="http://localhost:1000/transactions";
    public static void main(String[] args) {
        TransactionByUsername transactionByUsername=new TransactionByUsername();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url,transactionByUsername);
    }
}

