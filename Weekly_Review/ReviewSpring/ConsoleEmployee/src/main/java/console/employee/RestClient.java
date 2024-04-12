//package console.employee;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//
//public class RestClient {
//    public static void main(String[] args) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGetCreate = new HttpGet("http://localhost:8012/employees/create");
//        HttpGet httpGetAll = new HttpGet("http://localhost:8012/employees/allEmployee");
//        HttpGet httpGetId = new HttpGet("http://localhost:8012/employees/employeeId/{employeeId}");
//        HttpGet httpGetPinCode = new HttpGet("http://localhost:8012/employees/pincode/576117");
//        //CloseableHttpResponse response = httpClient.execute(httpGetCreate);
//        //CloseableHttpResponse response = httpClient.execute(httpGetAll);
//        //CloseableHttpResponse response = httpClient.execute(httpGetId);
//        CloseableHttpResponse response = httpClient.execute(httpGetPinCode);
//
//
////        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
//        try{
//            int statusCode = response.getStatusLine().getStatusCode();
//            System.out.println("HTTP Status Code: " + statusCode);
//
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                String json = EntityUtils.toString(entity);
//                System.out.println("Employee Details:");
//                System.out.println(json);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
package console.employee;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Scanner;

public class RestClient {
    private static final String BASE_URL = "http://localhost:8012/employees";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // Send HTTP GET request to create an employee
            //sendHttpGetRequest(httpClient, BASE_URL + "/create");

            // Send HTTP GET request to retrieve all employees
            //sendHttpGetRequest(httpClient, BASE_URL + "/allEmployee");

            // Send HTTP GET request to retrieve employee by ID

            Scanner scanner=new Scanner(System.in);
            String employeeId=scanner.nextLine();
            sendHttpGetRequest(httpClient, BASE_URL + "/employeeId/" + employeeId);

            // Send HTTP GET request to retrieve employees by pin code
//            String pinCode = "576117";
//            sendHttpGetRequest(httpClient, BASE_URL + "/pincode/" + pinCode);
        } finally {
            httpClient.close();
        }
    }

    private static void sendHttpGetRequest(CloseableHttpClient httpClient, String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("HTTP Status Code: " + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                System.out.println("Response Body:");
                System.out.println(json);

            }
        }
    }
}
