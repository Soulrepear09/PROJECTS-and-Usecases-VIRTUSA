package banking;


public class CurrentAccount extends AccountRecords {

    private double overdraftLimit;  // how much below zero you can go

    public CurrentAccount(String accountNumber, String holderName, double initialDeposit) {
        super(accountNumber, holderName, initialDeposit);
        this.overdraftLimit = 2000.0;
    }

    // Override withdraw to allow overdraft
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid Withdrawal amount.");
            return false;
        }
        if (amount > balance + overdraftLimit) {
            System.out.println("  Exceeds overdraft limit. Cannot withdraw.");
            return false;
        }
        balance -= amount;
        history.add(new BankTransaction("WITHDRAWAL", amount));
        System.out.printf("  Rs. %.2f withdrawn. New balance: Rs. %.2f%n", amount, balance);
        return true;
    }

    @Override
    public String getAccountType() {
        return "CURRENT ACCOUNT";
    }
}
