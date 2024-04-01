package jdbc.soapweb;
import jdbc.soapweb.configurations.SoapPhase;
import jdbc.soapweb.dao.TransactionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.transactions.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndpointsTesting {
    @MockBean
    private TransactionsService transactionsService;

    @InjectMocks
    private SoapPhase soapPhase;
    @Test
    public void testAddNewTransaction() throws DatatypeConfigurationException {
        Transactions transaction1=new Transactions(1,new Date(2024,03,24),"Annapoorna",1000,"Family","Bharathi");
        when(transactionsService.newTransaction(any(Transactions.class))).thenReturn(transaction1);

        NewTransactionRequest newTransaction=new NewTransactionRequest();
        services.transactions.Transactions transactions=new services.transactions.Transactions();
        transactions.setTransactionId(1);
        LocalDate date = LocalDate.of(2024,05,02);
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
        transactions.setTransactionDate(xmlGregorianCalendar);
        transactions.setTransactionBy("Annpoorna");
        transactions.setTransactionTo("Bharathi");
        transactions.setTransactionAmount(50000);
        transactions.setTransactionRemarks("Friend");
        newTransaction.setTransactions(transactions);
        NewTransactionResponse response=soapPhase.addTransactionRequest(newTransaction);
        assertTrue(transaction1.getTransactionId().equals(response.getTransactions().getTransactionId()));
    }//fail
    @Test
    public void testFindBySender() {
        List<Transactions> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transactions(1,new Date(2024,03,24),"Annapoorna",1000,"Family","Bharathi"));
        when(transactionsService.findBySender("Bharathi")).thenReturn(mockTransactions);
        FindBySenderRequest request = new FindBySenderRequest();
        request.setSender("Annapoorna");
        FindBySenderResponse response = soapPhase.findBySenderRequest(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions available", response.getServiceStatus().getMessage());
    }

    @Test
    public void testFindByReciever() {
        List<Transactions> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transactions(1,new Date(2024,03,24),"Annapoorna",1000,"Family","Bharathi"));
        when(transactionsService.findByReceiver("Annapoorna")).thenReturn(mockTransactions);
        FindByReceiverRequest request = new FindByReceiverRequest();
        request.setReceiver("udaya"); //fail
        FindByReceiverResponse response = soapPhase.findByReceiverRequest(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions available", response.getServiceStatus().getMessage());
    }

    @Test
    public void testFindByAmount() {
        List<Transactions> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transactions(1,new Date(2024,03,24),"Annapoorna",1000,"Family","Bharathi"));
        when(transactionsService.findByAmount(1000.0)).thenReturn(mockTransactions);
        FindByAmountRequest request = new FindByAmountRequest();
        request.getAmount(); //fail
        FindByAmountResponse response = soapPhase.findByAmountRequest(request);
        assertNotEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertNotEquals("Transactions available", response.getServiceStatus().getMessage());
        assertEquals(1, response.getTransactions().size());
    }


    @Test
    public void testUpdatingTransaction() {
        Transactions updateTransaction = new Transactions();
        updateTransaction.setTransactionId(2);
        updateTransaction.setTransactionDate(new Date(2024,03,24));
        updateTransaction.setTransactionBy("Sender");
        updateTransaction.setTransactionTo("Receiver");
        updateTransaction.setTransactionAmount(1000.0);
        updateTransaction.setTransactionRemarks("new remark");
        when(transactionsService.updateRemarks(any(Transactions.class))).thenReturn(updateTransaction);
        UpdateRemarksRequest request = new UpdateRemarksRequest();
        services.transactions.Transactions transaction = new services.transactions.Transactions();
        transaction.setTransactionId(2);
        transaction.setTransactionDate(new Date(2024,03,24));
        updateTransaction.setTransactionBy("Sender");
        updateTransaction.setTransactionTo("Receiver");
        updateTransaction.setTransactionAmount(1000L);
        updateTransaction.setTransactionRemarks("old remark");
        request.setTransactions(transaction);
        UpdateRemarksResponse response = soapPhase.updatingTransaction(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("1 \" has been updated\"", response.getServiceStatus().getMessage());
        assertEquals(1L, response.getTransactions().getTransactionId());
        assertEquals("Updated remarks", response.getTransactions().getTransactionAmount());
    }

    @Test
    public void testRemoveTransactionBetweenDates() {
        String startDate ="01/01/2024";
        String endDate ="01/31/2024";
        when(transactionsService.deleteTransaction(startDate, endDate)).thenReturn("remove");
        DeleteTransactionRequest request = new DeleteTransactionRequest();
        Date startDate = new Date(2024, 03, 29);
        Date endDate = new Date(2024, 04, 29);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        DeleteTransactionResponse response = soapPhase.deleteTransaction(request);
        assertEquals("removed", response.getServiceStatus().getStatus());
        assertEquals("removed", response.getServiceStatus().getMessage());
    }



}

