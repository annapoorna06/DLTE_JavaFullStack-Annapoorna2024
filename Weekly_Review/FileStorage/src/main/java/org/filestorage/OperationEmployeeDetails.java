package org.filestorage;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public interface OperationEmployeeDetails {
    void inputDetails() throws IOException, ClassNotFoundException;
    void displayInput(List<Employee> employees);
    //void create(List<Employee> employee) throws IOException;
}
