//package frontend.console;
//
//import backend.datarepo.DatabaseRepositoryImplementation;
//import backend.datarepo.details.Employee;
//import exception.EmployeeException;
//import frontend.console.pojo.EmployeeConsole;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.slf4j.Logger;
//
//import java.util.ArrayList;
//import java.util.List;
//
////import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//public class AppTest {
//
//    @Mock
//    DatabaseRepositoryImplementation databaseRepository;
//
//    @Mock
//    Logger logger;
//
////    @BeforeEach
////    public void setUp() {
////        MockitoAnnotations.initMocks(this);
////    }
//
//    @Test
//    public void testDisplayEmployeeById() throws EmployeeException {
//        EmployeeConsole expectedEmployee = new EmployeeConsole(testDisplayAllEmployees());
//        String employeeId = "123";
//        when(databaseRepository.displayBasedOnEmployeeId(employeeId)).thenReturn(expectedEmployee);
//
//        // Creating an instance of ConsoleAppNew
//        ConsoleAppNew consoleApp = new ConsoleAppNew();
//
//        // Invoking the method to test
//        Employee result = consoleApp.displayEmployeeById(employeeId, databaseRepository, logger);
//
//        // Verifying that the method was called with the correct argument
//        verify(databaseRepository).displayBasedOnEmployeeId(employeeId);
//
//        // Verifying that the logger was not used
//        verifyNoInteractions(logger);
//
//        // Asserting the result
//        assertEquals(expectedEmployee, result);
//    }
//
//    @Test
//    public void testDisplayAllEmployees() {
//        List<Employee> expectedEmployees = new ArrayList<>();
//        when(databaseRepository.read()).thenReturn(expectedEmployees);
//        ConsoleAppNew consoleApp = new ConsoleAppNew();
//
//        // Invoking the method to test
//        List<Employee> result = consoleApp.displayAllEmployees(databaseRepository, logger);
//
//        // Verifying that the method was called
//        verify(databaseRepository).read();
//
//        // Verifying that the logger was not used
//        verifyNoInteractions(logger);
//
//        // Asserting the result
//        assertEquals(expectedEmployees, result);
//    }
//
//    @Test
//    public void testDisplayEmployeesByPincode() throws EmployeeException {
//        EmployeeConsole expectedEmployee = new EmployeeConsole();
//        int pinCode = 12345;
//        when(databaseRepository.displayBasedOnPinCode(pinCode)).thenReturn(expectedEmployee);
//
//        // Creating an instance of ConsoleAppNew
//        ConsoleAppNew consoleApp = new ConsoleAppNew();
//
//        // Invoking the method to test
//        Employee result = consoleApp.displayEmployeesByPincode(pinCode, databaseRepository, logger);
//
//        // Verifying that the method was called with the correct argument
//        verify(databaseRepository).displayBasedOnPinCode(pinCode);
//
//        // Verifying that the logger was not used
//        verifyNoInteractions(logger);
//
//        // Asserting the result
//        assertEquals(expectedEmployee, result);
//    }
//}
