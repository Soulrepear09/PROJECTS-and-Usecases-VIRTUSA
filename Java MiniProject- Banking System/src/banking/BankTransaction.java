package banking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankTransaction {

    private final String Operationtype;       // e.g. "DEPOSIT", "WITHDRAWAL", "TRANSFER"
    private final double amount;
    private final String timestamp;  // when it happened

    public BankTransaction(String type, double amount) {
        this.Operationtype = type;
        this.amount = amount;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        this.timestamp = LocalDateTime.now().format(formatter);
    }

    public void printDetail() {
        System.out.printf("  [%s] %s -> Rs. %.2f%n", timestamp, Operationtype, amount);
    }
}
