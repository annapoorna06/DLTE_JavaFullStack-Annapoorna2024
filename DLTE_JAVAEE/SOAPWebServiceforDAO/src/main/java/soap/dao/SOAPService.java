package soap.dao;

import org.example.Entity.UserDetails;
import org.example.Middleware.DatabaseTarget;
import org.example.Remote.StorageTarget;
import org.example.Services.UserDetailsServices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SOAPService {
    private UserDetailsServices userDetailsServices;
    public SOAPService() throws SQLException, IOException {
        StorageTarget storageTarget = new DatabaseTarget();
        userDetailsServices =  new UserDetailsServices(storageTarget);
    }
    @WebMethod
    @WebResult(name = "Create")
    public void createAccount(@WebParam(name = "String1") String userName, @WebParam(name = "String2") String password, @WebParam(name = "Date") Date dateOfBirth, @WebParam(name = "String4") String address, @WebParam(name = "String5") String emailId, @WebParam(name = "Long1") Long phoneNumber){
        UserDetails userDetails = new UserDetails(userName,password,dateOfBirth,address,emailId, phoneNumber);
        userDetailsServices.calladdusers();
    }
    @WebMethod
    @WebResult(name = "FindByUsername")
    public FetchAccount findUser(@WebParam(name = "String") String username){
        FetchAccount fetchAccount=new FetchAccount();
        UserDetails userDetails= (UserDetails) userDetailsServices.callFindAllByUsers(username);
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(userDetails);
        fetchAccount.setUserDetailsList(userDetailsList);
        return fetchAccount;
    }
    @WebMethod
    @WebResult(name = "Update")
    public void updateAccount(@WebParam(name = "String1") String userName, @WebParam(name = "String2") String password, @WebParam(name = "Date") Date dateOfBirth, @WebParam(name = "String4") String address, @WebParam(name = "String5") String emailId,
                              @WebParam(name = "Long1") Long phoneNumber, @WebParam(name = "String6") String newPassword, @WebParam(name = "String7") String newAddress, @WebParam(name = "String8") String newEmailId, @WebParam(name = "Long2") Long newPhoneNumber) {
        UserDetails userDetails = (UserDetails) userDetailsServices.callFindAllByUsers(userName);

        if (newPassword != null && !newPassword.isEmpty()) {
            userDetails.setpassword(newPassword);
        }
        if (newAddress != null && !newAddress.isEmpty()) {
            userDetails.setaddress(newAddress);
        }
        if (newEmailId != null && !newEmailId.isEmpty()) {
            userDetails.setemailId(newEmailId);
        }
        if (newPhoneNumber != null) {
            userDetails.setphoneNumber(newPhoneNumber);
        }
        userDetailsServices.callUpdate(userDetails);
    }
}
