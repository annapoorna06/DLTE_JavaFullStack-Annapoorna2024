package jdbc.soapweb.configurations;

import jdbc.soapweb.dao.Transactions;
import jdbc.soapweb.dao.TransactionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transactions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url="http://transactions.services";
    @Autowired
    private TransactionsService transactionsService;

    //adding new Transactions
    @PreAuthorize("hasAnyAuthority('admin')")//adding security authorization only for admin
    @PayloadRoot(namespace = url,localPart = "newTransactionRequest")
    @ResponsePayload
    public NewTransactionResponse addTransactionRequest(@RequestPayload NewTransactionRequest newTransactionRequest){
        NewTransactionResponse newLoanResponse=new NewTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        services.transactions.Transactions actual= newTransactionRequest.getTransactions();
        Transactions daoTransactions=new Transactions();
        Date date=newTransactionRequest.getTransactions().getTransactionDate().toGregorianCalendar().getTime();
        daoTransactions.setTransactionDate(date);
        BeanUtils.copyProperties(actual,daoTransactions);
        daoTransactions=transactionsService.newTransaction(daoTransactions);
        if(daoTransactions!=null){
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransactions,actual);
            newLoanResponse.setTransactions(actual);
            serviceStatus.setMessage(actual.getTransactionId()+" has inserted");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId()+" hasn't inserted");
        }
        newLoanResponse.setServiceStatus(serviceStatus);
        return newLoanResponse;
    }
//finding transaction details using sender name
    @PreAuthorize("hasAnyAuthority('customer')")//adding security authorization only for customer
    @PayloadRoot(namespace = url,localPart = "findBySenderRequest")
    @ResponsePayload
    public FindBySenderResponse findBySenderRequest(@RequestPayload FindBySenderRequest findBySenderRequest){
        FindBySenderResponse findBySenderResponse=new FindBySenderResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transactions.Transactions> transactList=new ArrayList<>();
        List<Transactions> transactionsList=transactionsService.findBySender(findBySenderRequest.getSender());

        Iterator<Transactions> iterator=transactionsList.iterator();
        while (iterator.hasNext()){
            services.transactions.Transactions currentTransactions=new services.transactions.Transactions();

            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            transactList.add(currentTransactions);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");
        findBySenderResponse.setServiceStatus(serviceStatus);
        findBySenderResponse.getTransactions().addAll(transactList);
        return findBySenderResponse;
    }
    //finding transaction details using receiver name
    @PreAuthorize("hasAnyAuthority('customer')") //adding security authorization only for customer
    @PayloadRoot(namespace = url,localPart = "findByReceiverRequest")
    @ResponsePayload
    public FindByReceiverResponse findByReceiverRequest(@RequestPayload FindByReceiverRequest findByReceiverRequest){
        FindByReceiverResponse findByReceiverResponse=new FindByReceiverResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transactions.Transactions> transactList=new ArrayList<>();
        List<Transactions> transationsList=transactionsService.findByReceiver(findByReceiverRequest.getReceiver());

        Iterator<Transactions> iterator=transationsList.iterator();
        while (iterator.hasNext()){
            services.transactions.Transactions currentTransactions=new services.transactions.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            transactList.add(currentTransactions);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");
        findByReceiverResponse.setServiceStatus(serviceStatus);
        findByReceiverResponse.getTransactions().addAll(transactList);
        return findByReceiverResponse;
    }
    //finding list of transactions using amount
    @PreAuthorize("hasAnyAuthority('customer')")//adding security authorization only for customer
    @PayloadRoot(namespace = url,localPart = "findByAmountRequest")
    @ResponsePayload
    public FindByAmountResponse findByAmountRequest(@RequestPayload FindByAmountRequest findByAmountRequest){
        FindByAmountResponse findByAmountResponse=new FindByAmountResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transactions.Transactions> transactionList=new ArrayList<>();
        List<Transactions> transactionsList=transactionsService.findByAmount(findByAmountRequest.getAmount());

        Iterator<Transactions> iterator=transactionsList.iterator();
        while (iterator.hasNext()){
            services.transactions.Transactions currentTransactions=new services.transactions.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            transactionList.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");
        findByAmountResponse.setServiceStatus(serviceStatus);
        findByAmountResponse.getTransactions().addAll(transactionList);
        return findByAmountResponse;
    }
    //updating remarks in transactions
    @PreAuthorize("hasAnyAuthority('admin','manager')") //adding security authorization only for admin and manager together
    @PayloadRoot(namespace = url, localPart = "updateRemarksRequest")
    @ResponsePayload
    public UpdateRemarksResponse updatingTransaction(@RequestPayload UpdateRemarksRequest updateRemarksRequest){
        UpdateRemarksResponse updateRemarksResponse=new UpdateRemarksResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        services.transactions.Transactions transactions=new services.transactions.Transactions();
        Date date=updateRemarksRequest.getTransactions().getTransactionDate().toGregorianCalendar().getTime();

        Transactions daoTransactions=new Transactions();
        daoTransactions.setTransactionDate(date);
        BeanUtils.copyProperties(updateRemarksRequest.getTransactions(),daoTransactions);
        daoTransactions = transactionsService.updateRemarks(daoTransactions);
        if(daoTransactions!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(daoTransactions.getTransactionRemarks()+" has been updated");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(daoTransactions.getTransactionRemarks()+" hasn't been updated");
        }

        BeanUtils.copyProperties(daoTransactions,transactions);

        updateRemarksResponse.setServiceStatus(serviceStatus);
        updateRemarksResponse.setTransactions(transactions);

        return updateRemarksResponse;
    }
    //delete transaction between dates
    @PreAuthorize("hasAnyAuthority('admin')")//adding security authorization only for admin
    @PayloadRoot(namespace = url,localPart = "deletionTransactionRequest")
    @ResponsePayload
    public DeleteTransactionResponse deleteTransaction(@RequestPayload DeleteTransactionRequest deleteTransactionRequest){
        DeleteTransactionResponse deleteTransactionResponse=new DeleteTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        String message = transactionsService.deleteTransaction(deleteTransactionRequest.getStartDate(),deleteTransactionRequest.getEndDate());
        if(message.contains("Invalid"))
            serviceStatus.setStatus("FAILURE");
        else
            serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage(message);
        deleteTransactionResponse.setServiceStatus(serviceStatus);
        return deleteTransactionResponse;
    }

}
