package mybank.dao.entity;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoansAvailable {
    //entities form MYBANK_APP_LOANAVAILABLE table
    @NotNull(message="{loan.num.null}")
    @Digits(integer=3,fraction=0,message="{loan.num.invalid}")
    private int loanNumber;
    @NotNull(message = "{loan.type.null}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{loan.type.invalid}")
    private String loanType;
    @NotNull(message = "{loan.name.null}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{loan.name.invalid}")
    private String loanName;
    @NotNull(message = "{loan.desc.null}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{loan.desc.invalid}")
    private String loanDescription;
    @NotNull(message = "{loan.roi.null}")
    @Digits(integer = 8, fraction = 2, message = "{loan.roi.invalid}")
    private double loanRoi;

    //constructor
    public LoansAvailable(int loanNumber, String loanType, String loanName, String loanDescription, double loanRoi) {
        this.loanNumber = loanNumber;
        this.loanType = loanType;
        this.loanName = loanName;
        this.loanDescription = loanDescription;
        this.loanRoi = loanRoi;
    }

    //empty constructor
    public LoansAvailable() {

    }

    // to string
    @Override
    public String toString() {
        return "Loans{" +
                "loanNumber=" + loanNumber +
                ", loanType='" + loanType + '\'' +
                ", loanName='" + loanName + '\'' +
                ", loanDescription='" + loanDescription + '\'' +
                ", loanRoi=" + loanRoi +
                '}';
    }
    //getters and setters
    public int getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getLoanDescription() {
        return loanDescription;
    }

    public void setLoanDescription(String loanDescription) {
        this.loanDescription = loanDescription;
    }

    public double getLoanRoi() {
        return loanRoi;
    }

    public void setLoanRoi(double loanRoi) {
        this.loanRoi = loanRoi;
    }
}
