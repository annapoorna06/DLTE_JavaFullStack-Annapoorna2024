package company.banking.CreditCard;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class CustomerSupport {
    public static void main(String[] args) {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        CreditCard[] mybank={
                new CreditCard(23567876543L,"Annapoo",new Date(2024,1,20),8674,42000,new Date(2024,03,11),new Date(2024,03,8),1234),
                new CreditCard(33567876543L,"Akshira",new Date(2024,12,5),5674,50000,new Date(2024,06,4),new Date(2024,01,11),2234),
                new CreditCard(43567876543L,"Aru",new Date(2024,1,8),7674,62000,new Date(2024,06,5),new Date(2024,05,18),6223),
                new CreditCard(53567876543L,"Divija",new Date(2024,2,6),2674,750000,new Date(2025,01,23),new Date(2024,12,19),3234),
                new CreditCard(63567876543L,"Eeksha",new Date(2024,12,2),1674,7000,new Date(2025,03,11),new Date(2024,06,13),4234)
        };
        CustomerSupport support=new CustomerSupport();
        System.out.println("---------WELCOME TO MYBANK------------");
        Scanner scanner=new Scanner(System.in);
        System.out.println("1.Filter based on the given Limit");
        System.out.println("2.Filter based on the date of bill payment");
        System.out.println("3.Exit");
        System.out.println("Enter your Choice :");
        int choice=scanner.nextInt();
        switch (choice){
            case 1:try{
                System.out.println("Enter the Start Limit");
                int startLimit=scanner.nextInt();
                System.out.println("Enter the End Limit");
                int endLimit=scanner.nextInt();
                support.filterLimit(mybank,startLimit,endLimit);
            }catch (MyBankCreditCardException cardException){
                logger.log(Level.WARNING,cardException.toString());
            }
                break;
            case 2:try{
                System.out.println("Enter the Start Date");
                int startDate=scanner.nextInt();
                System.out.println("Enter the End Date");
                int endDate=scanner.nextInt();
                support.filterBasedOnBillPayment(mybank,startDate,endDate);
            }catch (MyBankCreditCardException cardException){
                logger.log(Level.WARNING,cardException.toString());
            }
                break;
            case 3: exit(0);

        }
        scanner.close();
    }
    public void filterLimit(CreditCard[] customer,int startLimit,int endLimit) throws MyBankCreditCardException {
        int flag=0;
        System.out.println(("Customer having the credit card limit between "+startLimit+" and "+endLimit));
        for(CreditCard each:customer){
            if(each.getCreditCardLimit()>=startLimit&& each.getCreditCardLimit()<=endLimit) {
                flag=1;
                System.out.println(each.getCreditCardHolder()+", Amount : "+each.getCreditCardLimit());

            }

        }
        if(flag==0){
            System.out.println("No customers found");
            throw new MyBankCreditCardException();
        }
    }
    public void filterBasedOnBillPayment(CreditCard[] customer,int startDate, int endDate) throws MyBankCreditCardException {
        int flag=0;
        System.out.println(("Customer who made bill Payment Between "+startDate+" and "+endDate));
        for(CreditCard each:customer){
            if(each.getDateOfBillPayment().getTime()>=startDate&& each.getDateOfBillPayment().getDate()<=endDate) {
                flag=1;
                System.out.println(each.getCreditCardHolder()+",Date : "+each.getDateOfBillPayment().getDate());

            }

        }
        if(flag==0){
            System.out.println("No customers found");
            throw new MyBankCreditCardException();
        }
    }
}
