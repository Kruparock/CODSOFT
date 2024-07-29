import java.util.Scanner;
public class ATM_Transaction {
    public static void main(String[] args) {
        int balance = 5000, withdraw, deposit;
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Automated Teller Machine");
            System.out.println("Choose 1 for Withdraw");
            System.out.println("Choose 2 for Deposit");
            System.out.println("Choose 3 for Balance");
            System.out.println("Choose 4 for Exit");
            System.out.println("Choose the opertion you want to perform:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter money to be withdrawn: ");
                    withdraw = scanner.nextInt();
                    if (balance >= withdraw) {
                        balance -= withdraw;
                        System.out.println("please collect your money");
                    } else {
                        System.out.println("Insufficient Balance");
                    }
                    System.out.println("");
                    break;

                case 2:
                    System.out.println("Enter money to be deposited:");
                    deposit = scanner.nextInt();
                    balance += deposit;
                    System.out.println("Your Money has been successfully deposited. ");
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Balance:"+balance);
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. please choose a valid option.");
                    System.out.println("");
                    break;
            }
        }
    }
}