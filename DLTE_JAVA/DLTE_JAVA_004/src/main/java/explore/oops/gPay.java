package explore.oops;

public class gPay extends DebitCard {
    public gPay(int accountNumber, double accountBalance, String accountHolder, String cardNumber, String cardPin) {
        super(accountNumber, accountBalance, accountHolder, cardNumber, cardPin);
    }
    //validates UPI Pin
    public boolean payBill(String billerName, double billedAmount, String billerType, String enteredUpiPin){
        if(!validatePin(enteredUpiPin)){
            System.out.println("Bill payment failed due to wrong UPI Pin");
            return false;
        }
        System.out.println("---GPay---");
        System.out.println("UPI pin approved!");
        System.out.println("Bill Payment approved for "+billerType+ ", \nBiller: "+billerName+" \nAmount: "+billedAmount);
        return true;
    }
}
