package banking;

import java.util.ArrayList;

// Abstract means you cannot create an Account directly — only SavingsAccount or CurrentAccount
public abstract class AccountRecords {

    protected String accountNumber;
    protected String holderName;
    protected double balance;
    protected ArrayList<BankTransaction> history;  // list of all transactions

    public AccountRecords(String accountNumber, String holderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialDeposit;
        this.history = new ArrayList<>();

        history.add(new BankTransaction("ACCOUNT OPENED", initialDeposit));
    }

    // Money Deposit
    public void deposit(double amount, boolean showMessage) {
        if (amount <= 0) {
            System.out.println("  Deposit amount must be positive.");
            return;
        }
        balance += amount;
        history.add(new BankTransaction("CREDIT", amount));
        if (showMessage) {
            System.out.printf("  Rs. %.2f deposited. New balance: Rs. %.2f%n", amount, balance);
        }
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid Amount.");
            return false;
        }
        if (amount > balance) {
            System.out.println("  Insufficient balance.");
            return false;
        }
        balance -= amount;
        history.add(new BankTransaction("DEBIT", amount));
        System.out.printf("  Rs. %.2f withdrawn. New balance: Rs. %.2f%n", amount, balance);
        return true;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void showBalance() {
        System.out.printf("  Account: %s | Holder: %s | Balance: Rs. %.2f%n",
                accountNumber, holderName, balance);
    }

    public void showHistory() {
        System.out.println("  --- Transaction History for " + accountNumber + " ---");
        for (BankTransaction t : history) {
            t.printDetail();
        }
    }

    public abstract String getAccountType();
}
