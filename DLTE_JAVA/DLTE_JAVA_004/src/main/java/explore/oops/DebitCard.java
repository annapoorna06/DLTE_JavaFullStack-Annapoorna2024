package explore.oops;

public class DebitCard extends Account{
    private String cardNumber;
    private String cardPin;

    public DebitCard(int accountNumber, double accountBalance, String accountHolder, String cardNumber, String cardPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }
    // validates card Pin for withdrawal

    public boolean validatePin(String enteredPin){
        return cardPin.equals(enteredPin);
    }
}
