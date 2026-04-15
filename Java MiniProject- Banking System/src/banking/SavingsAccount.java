package banking;

public class SavingsAccount extends AccountRecords {

    private final double interestRate;  // annual interest rate

    public SavingsAccount(String accountNumber, String holderName, double initialDeposit) {
        super(accountNumber, holderName, initialDeposit);  // call parent constructor
        this.interestRate = 0.04;  // default 4% interest
    }

    // Apply interest to the current balance
    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        history.add(new BankTransaction("INTEREST APPLIED", interest));
        System.out.printf("  Interest of Rs. %.2f applied. New balance: Rs. %.2f%n", interest, balance);
    }

    @Override
    public String getAccountType() {
        return "SAVINGS ACCOUNT";
    }
}
