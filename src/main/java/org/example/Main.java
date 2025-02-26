import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder receipt = new StringBuilder();

        String[][] coffeeMenu = {
                {"Espresso", "50.0"},
                {"Latte", "70.0"},
                {"Cappuccino", "65.0"},
                {"Mocha", "80.0"}
        };

        double totalPrice = 0;
        receipt.append("\n--- Coffee Order Receipt ---\n");

        while (true) {
            System.out.println("\n--- Coffee Menu ---");
            System.out.printf("%-3s %-12s %s%n", "No.", "Coffee", "Price (PHP)");
            System.out.println("------------------------------");
            for (int i = 0; i < coffeeMenu.length; i++) {
                System.out.printf("%-3d %-12s %.2f%n", (i + 1), coffeeMenu[i][0], Double.parseDouble(coffeeMenu[i][1]));
            }
            System.out.println("0 Finish Order");

            System.out.print("\nChoose your coffee (1-4, or 0 to finish): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            } else if (choice < 1 || choice > coffeeMenu.length) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            double itemTotal = Double.parseDouble(coffeeMenu[choice - 1][1]) * quantity;
            totalPrice += itemTotal;

            String orderLine = String.format("%dx %-12s - %.2f PHP%n", quantity, coffeeMenu[choice - 1][0], itemTotal);
            System.out.print(orderLine);
            receipt.append(orderLine);
        }

        receipt.append("\n--- Order Summary ---\n");
        receipt.append(String.format("Total Price: %.2f PHP%n", totalPrice));
        receipt.append("Thank you for your order!\n");

        System.out.println(receipt.toString());

        saveReceiptToFile(receipt.toString());

        scanner.close();
    }
    private static void saveReceiptToFile(String receiptContent) {
        try (FileWriter writer = new FileWriter("receipt.txt")) {
            writer.write(receiptContent);
            System.out.println("\nReceipt saved to receipt.txt");
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}
