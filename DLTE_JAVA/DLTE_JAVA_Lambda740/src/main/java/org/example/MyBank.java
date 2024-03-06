package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MyBank {
    List<Loan> loanList=new ArrayList<>();//creating list to use in main,
    void filterByRange(Date StartDate, Date EndDate);
}
