import java.util.List;
import java.util.Scanner;

public class InsuranceSoftware {
    private InsuranceAgreementManager agreementManager;
    private InsuranceApplicationGenerator applicationGenerator;

    public InsuranceSoftware() {
        // Initialize the agreement manager and application generator
        agreementManager = new InsuranceAgreementManager();
        applicationGenerator = new InsuranceApplicationGenerator();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the available options
            System.out.println("\n-- Insurance Buying Software --");
            System.out.println("1. Total amount of money");
            System.out.println("2. Receive new applications");
            System.out.println("3. See all current insurance agreements");
            System.out.println("4. See financial breakdown");
            System.out.println("5. Break insurance agreement");
            System.out.println("6. Move time forward");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayTotalMoney();
                    break;
                case 2:
                    generateApplications();
                    break;
                case 3:
                    displayInsuranceAgreements();
                    break;
                case 4:
                    displayFinancialBreakdown();
                    break;
                case 5:
                    breakInsuranceAgreement(scanner);
                    break;
                case 6:
                    moveTimeForward();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayTotalMoney() {
        // Display the total amount of money held by the agreement manager
        System.out.println("\nTotal amount of money: $" + agreementManager.getTotalMoney());
    }

    private void generateApplications() {
        System.out.println("\nNew Insurance Applications:");
        // Generate new insurance applications using the application generator
        List<InsuranceAgreement> applications = applicationGenerator.generateApplications(6);

        // Looping over our agreements list to display the details of each generated application
        for (int i = 0; i < applications.size(); i++) {
            InsuranceAgreement application = applications.get(i);
            System.out.println((i + 1) + ". Client Name: " + application.getClientName());
            System.out.println("   Yearly Fee: $" + application.getYearlyFee());
            System.out.println("   Risk: " + application.getRisk() + "%");
            System.out.println("   Insurance Amount: $" + application.getInsuranceAmount());
            System.out.println();
        }

        // Prompt the user to accept an application
        System.out.print("Enter the number of the application to accept (or 0 to skip): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= applications.size()) {

            // Add the accepted application to the agreement manager
            InsuranceAgreement acceptedApplication = applications.get(choice - 1);
            agreementManager.addAgreement(acceptedApplication);
            System.out.println("\nApplication accepted and added to insurance agreements.");
        } else if (choice != 0) {
            System.out.println("\nInvalid application number.");
        }
    }

    private void displayInsuranceAgreements() {

        // Displaying the details of all current insurance agreements managed by the agreement manager
        System.out.println("\nCurrent Insurance Agreements:");
        List<InsuranceAgreement> agreements = agreementManager.getAgreements();
        for (int i = 0; i < agreements.size(); i++) {
            InsuranceAgreement agreement = agreements.get(i);
            System.out.println((i + 1) + ". Client Name: " + agreement.getClientName());
            System.out.println("   Yearly Fee: $" + agreement.getYearlyFee());
            System.out.println("   Risk: " + agreement.getRisk() + "%");
            System.out.println("   Insurance Amount: $" + agreement.getInsuranceAmount());
            System.out.println("   Net Profit: $" + agreement.calculateNetProfit());
            System.out.println();
        }
    }

    private void displayFinancialBreakdown() {

        // Displaying the financial breakdown information from the agreement manager
        System.out.println("\nFinancial Breakdown:");
        System.out.println("Total Payouts from Claims: $" + agreementManager.getTotalPayouts());
        System.out.println("Total Payouts from Canceled Agreements: $" + agreementManager.getTotalCancelledPayouts());
        System.out.println("Total Income Made: $" + agreementManager.getTotalIncome());
        System.out.println("Breakdown of Net Profit per Year:");

        List<InsuranceAgreement> agreements = agreementManager.getAgreements();
        double totalNetProfit = 0;
        int year = 1;
        // Calculate and display the net profit for each agreement
        for (InsuranceAgreement agreement : agreements) {
            double netProfit = agreement.calculateNetProfit();
            totalNetProfit += netProfit;
            System.out.println("Year " + year + ": $" + netProfit);
            year++; //Incrementing the year everytime we move forward
        }

        double averageNetProfit = totalNetProfit / agreements.size();
        // Calculate and display the average net profit
        System.out.println("\nAverage Net Profit: $" + averageNetProfit);
    }

    private void breakInsuranceAgreement(Scanner scanner) {
        System.out.println("\nBreak Insurance Agreement:");
        displayInsuranceAgreements(); // Display the current insurance agreements

        System.out.print("Enter the number of the agreement to break: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= agreementManager.getAgreements().size()) {
            // Remove the chosen agreement from the agreement manager
            agreementManager.removeAgreement(choice - 1);
            System.out.println("\nInsurance agreement broken. Break fee paid.");
        } else {
            System.out.println("\nInvalid agreement number.");
        }
    }

    private void moveTimeForward() {
        System.out.println("\nMove Time Forward:");
        // Move time forward by one year in the agreement manager
        agreementManager.processTime();
        System.out.println("\nTime moved forward by one year. Yearly fees paid and claims processed.");
    }
}