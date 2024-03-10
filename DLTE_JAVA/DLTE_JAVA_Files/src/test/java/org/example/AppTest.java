package org.example;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private MyBank bankApp;
    static List<Loans> loanList=new ArrayList<>();
    static List<Loans> loan=new ArrayList<>();
    @Before
    public void setUp() {
        bankApp = new Implementation();
    }

    @BeforeClass
    public static void initialize(){
        loanList.add(new Loans(123,1000,"open","Annapoorna","9876543"));
        loanList.add(new Loans(223,2000,"open","Aru","9876543"));
        loanList.add(new Loans(323,3000,"closed","Divija","9876543"));
    }
    @Test
    public void testAddNewLoan() throws IOException, ClassNotFoundException {
        int initialSize = bankApp.checkAvailableLoans().size();
        bankApp.addNewLoan();
        int newSize = bankApp.checkAvailableLoans().size();
        assertEquals(initialSize + 1, newSize);
    }



    @Test
    public void testClosedLoan()  {
        String expectedLoanStatus="closed";
        assertNotEquals("Expected test to pass",expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(1).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(2).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(3).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(4).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(5).getLoanStatus());
    }

    @Test
    public void testOpenLoan() {
        // Assuming open loans have a status of "open"
        String expectedLoanStatus="open";
        assertNotEquals("Expected test to pass",expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(1).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(2).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(3).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(4).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(5).getLoanStatus());
    }
    @Test(timeout =1000)
    public void testTime() throws InterruptedException {
        Thread.sleep(100);
        assertTrue(loanList.size()>0);
    }
    @Test
    public void checkNull(){
        assertNull(loan);
        assertNotNull(loanList);
    }




}