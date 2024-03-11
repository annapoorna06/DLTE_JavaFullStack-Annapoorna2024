package First;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sipandtax/*")
public class SipandTax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestAnnualIncome=req.getParameter("Annual Income");//for tax slab
        String requestRegime=req.getParameter("Regime");//for tax slab
        
        String requestPrinciple=req.getParameter("Principle");
        String requestInterestRate=req.getParameter("Interest Return");
        String requestPeriod=req.getParameter("Period");
        //sip calculation
        if(requestPrinciple!=null && requestInterestRate!=null && requestPeriod!=null){
           double monthly=Double.parseDouble(requestPrinciple);
           double Return=Double.parseDouble(requestInterestRate);
           int totalPeriod=Integer.parseInt(requestPeriod);
            double monthlyInterestRate = Return/12/100;
            double totalReturn=monthly*((Math.pow((1+monthlyInterestRate),(12*totalPeriod))-1)*(1+monthlyInterestRate))/monthlyInterestRate;
            double totalMoneyInvested = (12*totalPeriod) * monthly;
            double EstimatedReturn =totalReturn-totalMoneyInvested;
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Estimated amount="+EstimatedReturn+ "Total return = "+ totalReturn+ "Total investment="+ totalMoneyInvested);
        }

        else{
            String received=findTax(Double.parseDouble(requestAnnualIncome),requestRegime);
            resp.getWriter().println(received);
        }
    }

    private String findTax(double annualIncome, String Regime) {
        double taxAmount=0;
        switch(Regime) {
            //tax slabs based on old tax regime
            case "old": case "Old": case "OLD":
                if (annualIncome <= 250000) {
                    System.out.println("No tax required to pay");
                } else if (annualIncome > 250000 & annualIncome <= 500000) {
                    System.out.println("You have to pay an tax of 5%");
                    taxAmount = annualIncome * 0.05;
                    System.out.println("Tax amount =" + taxAmount);
                } else if (annualIncome > 50000 & annualIncome <= 1000000) {
                    System.out.println("You have to pay an tax of 20%");
                    taxAmount = annualIncome * 0.2;
                    System.out.println("Tax amount =" + taxAmount);
                } else {
                    System.out.println("You have to pay an tax of 30%");
                    taxAmount = annualIncome * 0.3;
                    System.out.println("Tax amount =" + taxAmount);
                }
                break;
            case "new": case "New": case "NEW":
                //tax based on new tax regime
                if (annualIncome <= 300000) {
                    System.out.println("No tax required to pay");
                } else if (annualIncome >= 300001 & annualIncome <= 600000) {
                    System.out.println("You have to pay an tax of 5%");
                    taxAmount = annualIncome * 0.05;
                    System.out.println("Tax amount =" + taxAmount);
                } else if (annualIncome >= 600001 & annualIncome <= 900000) {
                    System.out.println("You have to pay an tax of 10%");
                    taxAmount = annualIncome * 0.1;
                    System.out.println("Tax amount =" + taxAmount);
                } else if (annualIncome >= 900001 & annualIncome <= 1200000) {
                    System.out.println("You have to pay an tax of 15%");
                    taxAmount = annualIncome * 0.15;
                    System.out.println("Tax amount =" + taxAmount);
                } else if (annualIncome >= 1200001 & annualIncome <= 1500000) {
                    System.out.println("You have to pay an tax of 20%");
                    taxAmount = annualIncome * 0.2;
                    System.out.println("Tax amount =" + taxAmount);
                } else {
                    System.out.println("You have to pay an tax of 30%");
                    taxAmount = annualIncome * 0.3;
                    System.out.println("Tax amount =" + taxAmount);
                }
                break;
        }
        return Double.toString(taxAmount);
    }
}
