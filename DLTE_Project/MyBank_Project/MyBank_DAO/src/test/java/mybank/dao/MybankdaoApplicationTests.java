package mybank.dao;

import mybank.dao.entity.LoansAvailable;
import mybank.dao.services.LoanServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MybankdaoApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private LoanServices loanServices;

    @Autowired
    private MybankdaoApplication mybankdaoApplication;

    @Test
    void testallAvailableLoans(){
        LoansAvailable loansAvailable1=new LoansAvailable(101,"Home","Home Loan","laons to build dream homes",11.5);
        LoansAvailable loansAvailable2=new LoansAvailable(102,"Personal","personal Loan","laons to build dream homes",8.5);
        List<LoansAvailable> expect= Stream.of(loansAvailable1,loansAvailable2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(LoanServices.LoanAvailableMapper.class))).thenReturn(expect);
        List<LoansAvailable> actual=loanServices.allAvailableLoans();
        assertEquals(expect,actual);
    }
}
