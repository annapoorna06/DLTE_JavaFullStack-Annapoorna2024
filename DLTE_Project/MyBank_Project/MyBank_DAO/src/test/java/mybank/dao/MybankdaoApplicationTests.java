//package mybank.dao;
//
//import mybank.dao.entity.LoansAvailable;
//import mybank.dao.exceptions.NoLoanDataException;
//import mybank.dao.services.LoanServices;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class MybankdaoApplicationTests {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private LoanServices loanServices;
//
//    @Autowired
//    private MybankdaoApplication mybankdaoApplication;
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
//}
////mvn test-compile test