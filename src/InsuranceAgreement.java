public class InsuranceAgreement {
    private String clientName;
    private double yearlyFee;
    private double risk;
    private double insuranceAmount;

    //Insurance agreement claim
    public InsuranceAgreement(String clientName, double yearlyFee, double risk, double insuranceAmount) {
        this.clientName = clientName;
        this.yearlyFee = yearlyFee;
        this.risk = risk;
        this.insuranceAmount = insuranceAmount;
    }

    public String getClientName() {
        return clientName;
    }

    public double getYearlyFee() {
        return yearlyFee;
    }

    public double getRisk() {
        return risk;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public boolean makeClaim() {
        double randomValue = Math.random() * 100; // Generate a random value between 0 and 100

        // Return true if the random value is less than or equal to the risk, indicating a claim is made
        return randomValue <= risk;
    }

    public double calculateNetProfit() {
        double payouts = makeClaim() ? insuranceAmount : 0; //Calculate the payouts based on whether a claim is made or not
        return yearlyFee - payouts;
    }
}
