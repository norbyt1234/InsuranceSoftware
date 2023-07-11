import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsuranceApplicationGenerator {
    public List<InsuranceAgreement> generateApplications(int count) {
        List<InsuranceAgreement> applications = new ArrayList<>(); // List to store generated applications
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String clientName = "Client " + (i + 1); //Generate a client name
            double yearlyFee = random.nextDouble() * 1000 + 500; // Generate a random yearly fee between 500 and 1500
            double risk = random.nextDouble() * 96 + 5; // Generate a random risk percentage between 5 and 100
            // Generate a random insurance amount between 5 times the yearly fee and 21 times the yearly fee
            double insuranceAmount = random.nextDouble() * 16 + 5 * yearlyFee;
            InsuranceAgreement application = new InsuranceAgreement(clientName, yearlyFee, risk, insuranceAmount);
            applications.add(application);
        }

        return applications; // Return the list of generated applications
    }
}
