//package mybank.dao;
//import mybank.dao.entity.LoansAvailable;
//import mybank.dao.exceptions.LoanServiceException;
//import mybank.dao.exceptions.NoLoanDataException;
//import mybank.dao.services.LoanServices;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.SqlParameter;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class MybankdaoApplicationTests {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private LoanServices loanServices;
//
//    //test cases for success
//    @Test
//    void testallAvailableLoans_Success(){
//        // Mocking the response from the database
//        List<LoansAvailable> mockLoanList = new ArrayList<>();
//        mockLoanList.add(new LoansAvailable(101, "Personal", "Personal loan", "personal expenses", 8.8));
//        mockLoanList.add(new LoansAvailable(102, "Gold", "Bank loan", "fulfil dreams ", 11.5));
//        mockLoanList.add(new LoansAvailable(103, "Education", "Vidhya Nidhi", "fulfil dreams ", 6.5));
//        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenReturn(mockLoanList);
//
//        // Calling the method under test
//        List<LoansAvailable> result = loanServices.allAvailableLoans();
//        //pass
//        assertEquals(3, result.size());//number of data mocked and gives the size in List
//        assertEquals("Education", result.get(2).getLoanType());
//
//    }
//    //test cases for failure
//    @Test
//    void testallAvailableLoans_Failure(){
//        // Mocking the response from the database
//        List<LoansAvailable> mockLoanList = new ArrayList<>();
//        mockLoanList.add(new LoansAvailable(101, "Personal", "Personal loan", "personal expenses", 8.8));
//        mockLoanList.add(new LoansAvailable(102, "Gold", "Bank loan", "fulfil dreams ", 11.5));
//        mockLoanList.add(new LoansAvailable(103, "Education", "Vidhya Nidhi", "fulfil dreams ", 6.5));
//        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenReturn(mockLoanList);
//
//        // Calling the method under test
//        List<LoansAvailable> result = loanServices.allAvailableLoans();
//        //assertEquals(2, result.size());//fails
//        assertEquals("Education", result.get(0).getLoanName());//fails
//    }
//    //test case for exception
//    @Test
//    void allAvailableLoan_NoDataFound() {
//        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenReturn(new ArrayList<>());
//        // Calling the method under test and expecting an exception
//        assertThrows(NoLoanDataException.class, () -> loanServices.allAvailableLoans());
//    }
//
//    @Test
//    public void testFindByLoanType_Success() {
//        // Mocking the response from the database
//        List<Object[]> returnedLoansList = new ArrayList<>();
//        Object[] loanData = new Object[]{"SUCCESS", "Personal loan", "personal expenses", BigDecimal.valueOf(8.8), BigDecimal.valueOf(101), "Personal"};
//        returnedLoansList.add(loanData);
//
//        // Mocking the jdbcTemplate.call() method
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class)))
//                .thenAnswer(invocation -> {
//                    CallableStatementCreator creator = invocation.getArgument(0);
//                    List<SqlParameter> sqlParameters = invocation.getArgument(1);
//                    Map<String, Object> returnedMap = new HashMap<>();
//                    returnedMap.put("loan_number", BigDecimal.valueOf(101));
//                    returnedMap.put("loan_type_out", "Personal");
//                    returnedMap.put("loan_name", "Personal loan");
//                    returnedMap.put("loan_description", "personal expenses");
//                    returnedMap.put("loan_roi", BigDecimal.valueOf(8.8));
//                    return returnedMap;
//                });
//        // Calling the method under test
//        List<LoansAvailable> result = loanServices.findByLoanType("Personal");
//        // Asserting the result
//        assertEquals(1, result.size());
//        assertEquals("Personal", result.get(0).getLoanType());
//    }
//    @Test
//    public void testFindByLoanType_Failure() {
//        // Mocking the response from the database
//        List<Object[]> returnedLoansList = new ArrayList<>();
//        Object[] loanData = new Object[]{"SUCCESS", "Personal loan", "personal expenses", BigDecimal.valueOf(8.8), BigDecimal.valueOf(101), "Personal"};
//        returnedLoansList.add(loanData);
//
//        // Mocking the jdbcTemplate.call() method
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class)))
//                .thenAnswer(invocation -> {
//                    CallableStatementCreator creator = invocation.getArgument(0);
//                    List<SqlParameter> sqlParameters = invocation.getArgument(1);
//                    Map<String, Object> returnedMap = new HashMap<>();
//                    returnedMap.put("loan_number", BigDecimal.valueOf(101));
//                    returnedMap.put("loan_type_out", "Gold");
//                    returnedMap.put("loan_name", "Gold loan");
//                    returnedMap.put("loan_description", "use the power of metal");
//                    returnedMap.put("loan_roi", BigDecimal.valueOf(9.8));
//                    return returnedMap;
//                });
//        // Calling the method under test
//        List<LoansAvailable> result = loanServices.findByLoanType("Personal");
//        // Asserting the result
//        assertEquals(4, result.size());
//        assertEquals("Personal", result.get(0).getLoanType());
//    }
//
//    @Test
//    void testFindByLoanType_NoLoanFound() {
//        // Mocking the response from the database to simulate no loan found
//        when(jdbcTemplate.call(any(), any())).thenReturn(Collections.emptyMap());
//        assertThrows(NoLoanDataException.class, () -> loanServices.findByLoanType("Gold"));
//    }
//
//
//    @Test
//    void testGetRateOfInterestByLoanType_Success() {
//        // Mocking the response from the database
//        // Adjust the mock data according to your needs
//        List<LoansAvailable> mockLoanList = new ArrayList<>();
//        mockLoanList.add(new LoansAvailable(101, "Personal", "Personal loan", "personal expenses", 8.8));
//        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenReturn(mockLoanList);
//
//        // Calling the method under test
//        double result = loanServices.getRateOfInterestByLoanName("Personal loan");
//
//        // Asserting the result
//        assertEquals(8.8, result);
//    }
//    @Test
//    void testGetRateOfInterestByLoanType_Failure() {
//        // Mocking the response from the database
//        // Adjust the mock data according to your needs
//        List<LoansAvailable> mockLoanList = new ArrayList<>();
//        mockLoanList.add(new LoansAvailable(101, "Gold", "Gold loan", "personal expenses", 10.8));
//        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenReturn(mockLoanList);
//
//        // Calling the method under test
//        double result = loanServices.getRateOfInterestByLoanName("Gold loan");
//
//        // Asserting the result
//        assertEquals(8.8, result);
//    }
//    @Test
//    void testGetRateOfInterestByLoanType_Exception() {
//        // Mocking the response from the database to simulate an exception
//        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenThrow(new RuntimeException());
//
//        // Calling the method under test and expecting an exception
//        assertThrows(LoanServiceException.class, () -> loanServices.getRateOfInterestByLoanName("Agriculture"));
//    }
//}
