package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class MyBankContext {
    @Autowired
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("mybank-branches.xml");
        Branch Branch3 = applicationContext.getBean("branch5", Branch.class);
        System.out.println(Branch3.getIfsCode() + " " + Branch3.getBranchName());
    }
}
