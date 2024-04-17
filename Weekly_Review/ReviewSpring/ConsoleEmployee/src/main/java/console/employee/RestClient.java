package console.employee;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Scanner;

public class RestClient {

    private static final String BASE_URL = "http://localhost:8012/employees/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Read All Employees");
            System.out.println("2. Display Employee by ID");
            System.out.println("3. Display Employee by Pin Code");
            System.out.println("4. Create employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    readAllEmployees(httpClient);
                    break;
                case 2:
                    displayEmployeeById(httpClient, scanner);
                    break;
                case 3:
                    displayEmployeeByPinCode(httpClient, scanner);
                    break;
                case 4:
                    createEmployees(httpClient, scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }

        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAllEmployees(CloseableHttpClient httpClient) {
        try {
            HttpGet httpGet = new HttpGet(BASE_URL + "allEmployee");
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("HTTP Status Code: " + statusCode);

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String json = EntityUtils.toString(entity);
                    System.out.println("Employee Details:");
                    System.out.println(json);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayEmployeeById(CloseableHttpClient httpClient, Scanner scanner) {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();

        try {
            HttpGet httpGet = new HttpGet(BASE_URL + "employeeId/" + employeeId);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("HTTP Status Code: " + statusCode);

                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String json = EntityUtils.toString(entity);
                        System.out.println("Employee Details:");
                        System.out.println(json);
                    }
                } else if (statusCode == 404) {
                    System.out.println("Employee not found.");
                } else {
                    System.out.println("Error occurred. HTTP Status Code: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayEmployeeByPinCode(CloseableHttpClient httpClient, Scanner scanner) {
        System.out.print("Enter employee pin code: ");
        int pinCode = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        try {
            HttpGet httpGet = new HttpGet(BASE_URL + "pincode/" + pinCode);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("HTTP Status Code: " + statusCode);

                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String json = EntityUtils.toString(entity);
                        System.out.println("Employee Details:");
                        System.out.println(json);
                    }
                } else if (statusCode == 204) {
                    System.out.println("No employees found for the given pin code.");
                } else {
                    System.out.println("Error occurred. HTTP Status Code: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void createEmployees(CloseableHttpClient httpClient, Scanner scanner) {
        System.out.println("Enter employee data in JSON format:");
        String jsonData = scanner.nextLine();

        try {
            HttpPost httpPost = new HttpPost(BASE_URL + "create");
            StringEntity entity = new StringEntity(jsonData);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("HTTP Status Code: " + statusCode);

                if (statusCode == 201) {
                    HttpEntity responseEntity = response.getEntity();
                    if (responseEntity != null) {
                        String jsonResponse = EntityUtils.toString(responseEntity);
                        System.out.println("Created Employees:");
                        System.out.println(jsonResponse);
                    }
                } else {
                    System.out.println("Error occurred. HTTP Status Code: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
