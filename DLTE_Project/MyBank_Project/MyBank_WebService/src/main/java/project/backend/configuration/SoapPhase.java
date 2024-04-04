package project.backend.configuration;

import mybank.dao.entity.LoansAvailable;
import mybank.dao.interfaces.LoansInterface;
import mybank.dao.services.LoanServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.loans.ServiceStatus;
import services.loans.ViewAllAvailableLoanRequest;
import services.loans.ViewAllAvailableLoanResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ComponentScan("mybank.dao")
@Endpoint
public class SoapPhase {
    @Autowired
    public LoansInterface interfaceServices;
    @Autowired
    public static LoanServices loanServices;

    private final String url = "http://loans.services";

    @PayloadRoot(namespace = url, localPart = "viewAllAvailableLoanRequest")
    @ResponsePayload
    public ViewAllAvailableLoanResponse viewAvailLoanRequest(@RequestPayload ViewAllAvailableLoanRequest request) {
        ViewAllAvailableLoanResponse response = new ViewAllAvailableLoanResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        List<LoansAvailable> allLoansDao = interfaceServices.allAvailableLoans();

        List<services.loans.LoanAvailable> allLoans = new ArrayList<>();

        Iterator<LoansAvailable> iterator = allLoansDao.iterator();
        while (iterator.hasNext()) {
            services.loans.LoanAvailable currentLoan = new services.loans.LoanAvailable();
            BeanUtils.copyProperties(iterator.next(), currentLoan);
            allLoans.add(currentLoan);

        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Loan's were fetched");
        response.getLoanAvailable().addAll(allLoans);
        response.setServiceStatus(serviceStatus);

        return response;

    }
}
