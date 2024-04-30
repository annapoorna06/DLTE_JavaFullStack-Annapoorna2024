package project.backend.configuration;
import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import mybank.dao.services.LoanServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.loans.ServiceStatus;
import services.loans.ViewAllAvailableLoanRequest;
import services.loans.ViewAllAvailableLoanResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ComponentScan("mybank.dao")
@Endpoint
@CrossOrigin(origins = "*")
public class SoapPhase {
    @Autowired
    public LoansInterface interfaceServices;
    Logger logger= LoggerFactory.getLogger(LoanServices.class);

    private final String url = "http://loans.services";
    ResourceBundle resourceBundle=ResourceBundle.getBundle("apps");
    //service to fetch all the available loans
    @PayloadRoot(namespace = url, localPart = "viewAllAvailableLoanRequest")
    @ResponsePayload
    public ViewAllAvailableLoanResponse viewAvailLoanRequest(@RequestPayload ViewAllAvailableLoanRequest request){
        ViewAllAvailableLoanResponse response=new ViewAllAvailableLoanResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        try {
            List<LoansAvailable> allLoansDao = interfaceServices.allAvailableLoans();
            List<services.loans.LoanAvailable> allLoans=new ArrayList<>();
            //java8 features
            allLoansDao.forEach(each->{
                services.loans.LoanAvailable currentLoan=new services.loans.LoanAvailable();
                BeanUtils.copyProperties(each,currentLoan);
                allLoans.add(currentLoan);
            });
            //sets https status code-200
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            response.getLoanAvailable().addAll(allLoans);
            logger.info(resourceBundle.getString("loan.server.available"));
        }catch (LoanServiceException exception){
            //sets https status code-500
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(exception.toString());
            logger.info(resourceBundle.getString("loan.server.error"));
        }
        catch (NoLoanDataException exception){
            //sets https status code-500
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage(exception.toString());
            logger.info(resourceBundle.getString("loan.server.error"));
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
