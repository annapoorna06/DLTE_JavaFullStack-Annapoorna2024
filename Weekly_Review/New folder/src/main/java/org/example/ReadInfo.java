package org.example;

import java.io.IOException;
import java.util.List;

public interface ReadInfo {
    List<Employee> readEmployee() throws IOException;
    List<EmployeePermanentAddress>readPermanentAddress() throws IOException;
    void readTemporaryAddress();
}
