import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Class representing a bank account
class Account {
    private String accountHolder;
    private int accountNumber;
    private double balance;

    // Constructor to create a new account
    public Account(String accountHolder, int accountNumber, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("💵 Successfully deposited $" + amount);
        } else {
            System.out.println("⚠️ Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("💸 Successfully withdrew $" + amount);
        } else {
            System.out.println("⚠️ Insufficient funds or invalid amount.");
        }
    }

    // Method to check the balance
    public double getBalance() {
        return balance;
    }

    // Getter for account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Getter for account holder's name
    public String getAccountHolder() {
        return accountHolder;
    }
}

// Main banking system class
public class BankingSystem {
    // Map to store accounts using account number as key
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    // Method to generate a unique 8-digit account number
    public static int generateAccountNumber() {
        int accountNumber;
        do {
            accountNumber = 10000000 + random.nextInt(90000000);  // Generates an 8-digit number
        } while (accounts.containsKey(accountNumber));  // Ensure uniqueness
        return accountNumber;
    }

    // Method to create a new account
    public static void createAccount() {
        System.out.println("Enter account holder's name:");
        String name = scanner.nextLine();
        int accountNumber = generateAccountNumber();  // Generate a unique 8-digit account number
        System.out.println("Enter an initial deposit amount:");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        // Create and store a new account
        Account newAccount = new Account(name, accountNumber, initialDeposit);
        accounts.put(accountNumber, newAccount);
        System.out.println("✅ Account created successfully for " + name + " with Account Number: " + accountNumber);
    }

    // Method to deposit money
    public static void depositMoney() {
        System.out.println("Enter account number:");
        int accountNumber = scanner.nextInt();
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Enter amount to deposit:");
            double amount = scanner.nextDouble();
            accounts.get(accountNumber).deposit(amount);
        } else {
            System.out.println("⚠️ Account not found.");
        }
    }

    // Method to withdraw money
    public static void withdrawMoney() {
        System.out.println("Enter account number:");
        int accountNumber = scanner.nextInt();
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Enter amount to withdraw:");
            double amount = scanner.nextDouble();
            accounts.get(accountNumber).withdraw(amount);
        } else {
            System.out.println("⚠️ Account not found.");
        }
    }

    // Method to check the account balance
    public static void checkBalance() {
        System.out.println("Enter account number:");
        int accountNumber = scanner.nextInt();
        if (accounts.containsKey(accountNumber)) {
            double balance = accounts.get(accountNumber).getBalance();
            System.out.println("💰 Balance: $" + balance);
        } else {
            System.out.println("⚠️ Account not found.");
        }
    }

    // Main menu to navigate through options
    public static void showMenu() {
        int option;
        do {
            System.out.println("\n🏦 Welcome to the Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");

            while (!scanner.hasNextInt()) {
                System.out.println("⚠️ Invalid option. Please enter a number.");
                scanner.next(); // Clear invalid input
            }

            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("👋 Thank you for using the banking system. Goodbye!");
                    break;
                default:
                    System.out.println("⚠️ Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    // Main method to start the banking system
    public static void main(String[] args) {
        showMenu();
    }
}
