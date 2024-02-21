package store.oops;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
        private Date dateOfTransaction;
        private double amountInTransaction;
        private String transactionTo;
        private String remarks;


}
