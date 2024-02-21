package store.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data //annotation used for setter and getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    //collection of objects
    private Long creditCardNumber;
    private String creditCardHolder;
    private Date creditCardExpiry;
    private Integer creditCardCvv;
    private Integer creditCardLimit;
    private Date dateOfBillGeneration;
    private Date dateOfBillPayment;
    private Integer creditCardPin;


}
