import java.util.ArrayList;
import java.util.List;

public class InsuranceAgreementManager {
    private List<InsuranceAgreement> agreements;
    private double totalMoney;
    private double totalPayouts;
    private double totalCancelledPayouts;

    public InsuranceAgreementManager() {
        agreements = new ArrayList<>(); // Initialize the list of agreements
        totalMoney = 0;
        totalPayouts = 0;
        totalCancelledPayouts = 0;
    }

    public void addAgreement(InsuranceAgreement agreement) {
        agreements.add(agreement);
        totalMoney += agreement.getYearlyFee(); //Increase total money by yearly fee
    }

    public void removeAgreement(int index) {
        InsuranceAgreement agreement = agreements.remove(index);
        double breakFee = 10 * agreement.getYearlyFee();
        totalMoney -= breakFee; //Deduct break fee if agreement broken
        totalCancelledPayouts += agreement.getInsuranceAmount();
    }

    public void processTime() {
        for (InsuranceAgreement agreement : agreements) {
            totalMoney += agreement.getYearlyFee(); //Increase the total by yearly fee
            if (agreement.makeClaim()) { // Check if the agreement makes a claim
                totalPayouts += agreement.getInsuranceAmount(); //Increase total payouts by amount of agreement
                totalMoney -= agreement.getInsuranceAmount(); //Deduct insurance amount from total money
            }
        }
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public double getTotalPayouts() {
        return totalPayouts;
    }

    public double getTotalCancelledPayouts() {
        return totalCancelledPayouts;
    }

    public double getTotalIncome() {
        return totalMoney - totalPayouts - totalCancelledPayouts; //Calculate and return the total income
    }

    public List<InsuranceAgreement> getAgreements() {
        return agreements;
    }
}
