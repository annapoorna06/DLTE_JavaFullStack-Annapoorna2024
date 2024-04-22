package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.FetchTransactions;
import service.TransactionByUsername;
import service.TransactionByUsernameService;
import service.Transactions;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    @Mock
    private TransactionByUsername transactionByUsername;

    @Mock
    private TransactionByUsernameService transactionByUsernameService;

    @Test
    public void testMain() {
        // Mocking service response
//        List<Transactions> mockTransactions = Arrays.asList(
//                new Transactions("annapoornapai", "transaction1"),
//                new Transactions("annapoornapai", "transaction2")
//        );

        // Mocking service call
//        when(transactionByUsernameService.getTransactionByUsernamePort()).thenReturn(transactionByUsername);
//        when(transactionByUsername.findAllByUsers("annapoornapai")).thenReturn((FetchTransactions) mockTransactions);
//        // Verifying output
//        assertEquals("Expected transactions", mockTransactions, App.transactions);
    }
}
