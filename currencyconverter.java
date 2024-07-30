import java.util.Scanner;
public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the base currency (e.g., USD, EUR, GBP): ");
        String baseCurrency = scanner.nextLine();
        System.out.println("Select the target currency (e.g., USD, EUR, GBP): ");
        String targetCurrency = scanner.nextLine();
        System.out.println("Enter the amount to convert: ");
        double amount = scanner.nextDouble();
        double exchangeRate = 1.0; 
        double convertedAmount = amount * exchangeRate;
        System.out.println("Converted Amount: " + convertedAmount + " " + targetCurrency);
    }
}