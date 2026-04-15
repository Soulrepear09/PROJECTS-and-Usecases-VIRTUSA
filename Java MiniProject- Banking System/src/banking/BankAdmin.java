package banking;

import java.util.HashMap;


public class BankAdmin {

    private HashMap<String, AccountRecords> accounts;   // accountNumber
    private HashMap<String, UserDetails> users;         // username → UserDetails
    private int accountregCounter;                  // used to generate unique account numbers

    public BankAdmin() {
        accounts = new HashMap<>();
        users = new HashMap<>();
        accountregCounter = 1000;
    }

    private String generateAccountNumber() {
        accountregCounter++;
        return "PX" + accountregCounter;
    }


    public String createAccount(String holderName, String username, String password, String type) {
        if (users.containsKey(username)) {
            System.out.println("  Username already taken. Try another.");
            return null;
        }

        String accNum = generateAccountNumber();
        AccountRecords newAccount;

        if (type.equalsIgnoreCase("savings")) {
            newAccount = new SavingsAccount(accNum, holderName, 500.0);  // Rs. 500 opening balance
        } else {
            newAccount = new CurrentAccount(accNum, holderName, 1000.0); // Rs. 1000 opening balance
        }

        accounts.put(accNum, newAccount);
        users.put(username, new UserDetails(username, password, accNum));

        System.out.println("  Account created successfully!");
        System.out.println("  Your Account Number: " + accNum);
        return accNum;
    }

    // Login: returns the Account if credentials are correct, else null
    public AccountRecords login(String username, String password) {
        UserDetails user = users.get(username);
        if (user == null) {
            System.out.println("  User not found.");
            return null;
        }
        if (!user.authenticate(password)) {
            System.out.println("  Wrong password.");
            return null;
        }
        return accounts.get(user.getLinkedAccountNumber());
    }

    // Transfer money between two accounts
    public void transfer(AccountRecords sender, String targetAccNum, double amount) {
        AccountRecords receiver = accounts.get(targetAccNum);
        if (receiver == null) {
            System.out.println("  Target account not found.");
            return;
        }
        if (sender.getAccountNumber().equals(targetAccNum)) {
            System.out.println("  Cannot transfer to the same account.");
            return;
        }
        boolean success = sender.withdraw(amount);
        if (success) {
            receiver.deposit(amount, false);
            System.out.println("  Transfer completed to " + targetAccNum);
        }
    }
}
