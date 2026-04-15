package banking;

import java.util.Scanner;

public class BankDashboard {

    static Scanner sc = new Scanner(System.in);
    static BankAdmin bank = new BankAdmin();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    PIXER Bank Welcomes You!!   ");
        System.out.println("========================================");

        while (true) {
            System.out.println("\n  1. Create New Account");
            System.out.println("  2. Login to Existing Account");
            System.out.println("  3. Exit");
            System.out.print("  Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> createAccountMenu();
                case "2" -> loginMenu();
                case "3" -> {
                    System.out.println("  Thank you for using SimpleBank. Goodbye!");
                    return;
                }
                default -> System.out.println("  Invalid option. Try again.");
            }
        }
    }

    static void createAccountMenu() {
        System.out.print("  Enter your full name: ");
        String name = sc.nextLine().trim();

        System.out.print("  Choose username: ");
        String uname = sc.nextLine().trim();

        System.out.print("  Set password (6-digit number only): ");
        String pwd = sc.nextLine().trim();
        while(!pwd.matches("\\d{6}")){
            System.out.println(" Invalid! Password must be exactly 6 digits (e.g 223344)");
            System.out.println(" Set password (6 digits only)");
            pwd=sc.nextLine().trim();
        }

        System.out.print("  Account type (savings/current): ");
        String type = sc.nextLine().trim();

        bank.createAccount(name, uname, pwd, type);
    }

    static void loginMenu() {
        System.out.print("  Username: ");
        String uname = sc.nextLine().trim();

        System.out.print("  Password: ");
        String pwd = sc.nextLine().trim();
        while (!pwd.matches("\\d{6}")) {
            System.out.println("  Invalid! Password must be exactly 6 digits.");
            System.out.print("  Password (6 digits): ");
            pwd = sc.nextLine().trim();
        }

        AccountRecords acc = bank.login(uname, pwd);
        if (acc == null) {
            int attempts = 1;
            while (attempts < 7) {
                System.out.println("  Login failed. " + (7 - attempts) + " attempt(s) remaining." );
                System.out.print("  Password (6 digits): ");
                pwd = sc.nextLine().trim();
                while (!pwd.matches("\\d{6}")) {
                    System.out.println("  Invalid! Password must be exactly 6 digits." );
                    System.out.print("  Password (6 digits): ");
                    pwd = sc.nextLine().trim();
                }
                acc = bank.login(uname, pwd);
                if (acc != null) break;
                attempts++;
            }

            if (acc == null) {
                System.out.println("  Too many failed attempts. Account locked. Contact bank.");
                return;
            }
        }


        System.out.println("  Login successful! Welcome.");
        accountDashboard(acc);
    }

    static void accountDashboard(AccountRecords acc) {
        while (true) {
            System.out.println("\n  --- USER ACCOUNT PORTAL ---");
            System.out.println("  1. Check Balance");
            System.out.println("  2. Deposit");
            System.out.println("  3. Withdraw");
            System.out.println("  4. Transfer Money");
            System.out.println("  5. Transaction History");

            if (acc instanceof SavingsAccount) {
                System.out.println("  6. Apply Interest");
            }

            System.out.println("  0. Logout");
            System.out.print("  Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> acc.showBalance();

                case "2" -> {
                    double amt = readAmount("  Amount to deposit: Rs. ");
                    if (amt > 0) acc.deposit(amt, true);
                }

                case "3" -> {
                    double amt = readAmount("  Amount to withdraw: Rs. ");
                    if (amt > 0) acc.withdraw(amt);
                }

                case "4" -> {
                    System.out.print("  Enter target account number: ");
                    String target = sc.nextLine().trim();
                    double amt = readAmount("  Amount to transfer: Rs. ");
                    if (amt > 0){
                        if (amt > 10000){
                            System.out.println(" Transfer limit exceeded. Max transfer amount is Rs. 10,000");
                        } else {
                            bank.transfer(acc, target, amt);
                        }
                    }
                }

                case "5" -> acc.showHistory();

                case "6" -> {
                    if (acc instanceof SavingsAccount sa) {
                        sa.applyInterest();
                    } else {
                        System.out.println("  Not available for this account type.");
                    }
                }

                case "0" -> {
                    System.out.println("  Logged out.");
                    return;
                }

                default -> System.out.println("  Invalid option.");
            }
        }
    }

    static double readAmount(String prompt) {
        System.out.print(prompt);
        try {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("  No amount entered. Please type a number.");
                return -1;
            }
            double value = Double.parseDouble(input);
            if (value <= 0) {
                System.out.println("  Amount must be greater than zero.");
                return -1;
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println("  Invalid input. Please enter a number like 500 or 1000.50");
            return -1;
        }
    }
}