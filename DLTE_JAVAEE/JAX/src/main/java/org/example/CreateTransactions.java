package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class CreateTransactions {
    public static void main(String[] args) {
        List<Transactions> transactions = new ArrayList<>();
        transactions.add(new Transactions("Annapoorna",  new Date(), 100.0));
        transactions.add(new Transactions("Arundhathi", new Date(), 200.0));
        transactions.add(new Transactions("Akshira", new Date(), 150.0));
        transactions.add(new Transactions("Akshatha",new Date(), 1000));
        transactions.add(new Transactions("Raksha", new Date(), 2030));

        TransactionsWrapper wrapper = new TransactionsWrapper();
        wrapper.setTransactions(transactions);

        try {
            JAXBContext context = JAXBContext.newInstance(TransactionsWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapper, new File("transactions.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @XmlRootElement
    public static class TransactionsWrapper {
        private List<Transactions> transactions;

        @XmlElement(name = "transaction")
        public List<Transactions> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<Transactions> transactions) {
            this.transactions = transactions;
        }
    }
}
