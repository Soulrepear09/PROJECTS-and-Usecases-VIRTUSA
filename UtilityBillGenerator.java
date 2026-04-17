import java.util.Scanner;

interface Billable {
     double calculateTotal();
 }

 class UtilityBill implements Billable {
    String Name;
    int currentReading;
    int previousReading;
    int unitsConsumed;
    int tax = 5;

    UtilityBill(String name, int prev, int curr) {
        this.Name= name;
        this.previousReading=prev;
        this.currentReading=curr;
        this.unitsConsumed=curr-prev;
    }
     public double calculateTotal(){
        double billAmount=0;
        if(unitsConsumed <= 100){
            billAmount=unitsConsumed*1.0;
        }
        else if(unitsConsumed <= 300){
            billAmount = (100 * 1.0) + ((unitsConsumed-100)*2.0);
        }
        else {
            billAmount=(100*1.0)+(200*2.0)+((unitsConsumed-300)*5.0);
        }
        return billAmount;
     }
     double taxamount() {
        return calculateTotal() * tax / 100;
     }
     double getTotal() {
        return calculateTotal() + taxamount();
     }

     void billRecipt(){
        System.out.println("   UTILITY BILL RECIPT   ");
        System.out.println("---------------------------");
        System.out.println("Custome Name : " + Name);
        System.out.println("Previous Reading : " + previousReading);
        System.out.println("Present Reading : " + currentReading);
        System.out.println("Units Consumed : " + unitsConsumed);
        System.out.println("Bill : " + calculateTotal());
        System.out.println("Tax(5%) : " + taxamount());
        System.out.println(" Total Bill : " + getTotal());
     }

 }

public class UtilityBillGenerator {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Utility bill Generator");
        System.out.println(" Type 'Exit' as customer name to quit.\n");

        while(true){
            System.out.println("Enter Name: ");
            String name = sc.nextLine();

            if (name.equalsIgnoreCase("Exit")){
                System.out.println(" Thank you ! Leaving the system");
                break;
            }

            System.out.println(" Enter previous reading : ");
            int prev = sc.nextInt();

            System.out.println("Enter current reading : ");
            int curr = sc.nextInt();

            sc.nextLine();

            if(prev > curr){
                System.out.println(" Previous reading is cannot be greater than current reading!");
                continue;
            }
            UtilityBill amt = new UtilityBill(name, prev, curr);
            amt.billRecipt();
        }
        sc.close();
    }
}
