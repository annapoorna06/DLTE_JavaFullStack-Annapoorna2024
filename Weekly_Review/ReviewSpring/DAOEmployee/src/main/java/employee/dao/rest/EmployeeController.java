//package employee.dao.rest;
//
//import employee.dao.entity.Employee;
//import employee.dao.exception.EmployeeException;
//import employee.dao.services.Implementation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/employees")
//public class EmployeeController {
//
//    @Autowired
//    private Implementation employeeRepository;
//
//    @PostMapping("/create")
//    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> employees) {
//        try {
//            List<Employee> createdEmployees = employeeRepository.create(employees);
//            return new ResponseEntity<>(createdEmployees, HttpStatus.CREATED);
//        } catch (EmployeeException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("allEmployee")
//    public ResponseEntity<List<Employee>> getAllEmployees() {
//        try {
//            List<Employee> employees = employeeRepository.read();
//            if (employees.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(employees, HttpStatus.OK);
//        } catch (EmployeeException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/employeeId{employeeId}")
//    public ResponseEntity<Employee> displayBasedOnEmployeeId(@PathVariable String employeeId) {
//        try {
//            Employee employee = employeeRepository.displayBasedOnEmployeeId(employeeId);
//            if (employee == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(employee, HttpStatus.OK);
//        } catch (EmployeeException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/pincode/{pinCode}")
//    public ResponseEntity<List<Employee>> displayBasedOnPinCode(@PathVariable int pinCode) {
//        try {
//            List<Employee> employees = employeeRepository.displayBasedOnPinCode(pinCode);
//            if (employees.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(employees, HttpStatus.OK);
//        } catch (EmployeeException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
