package OOSE_H1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Step 1. Enter amount of all accounts = ");
            int numberOfAccounts = scanner.nextInt();

            while (numberOfAccounts < 5) {
                System.out.println("The number of accounts must be at least 5. Please enter again.");
                System.out.print("Step 1. Enter amount of all accounts = ");
                numberOfAccounts = scanner.nextInt();
            }

            scanner.nextLine();
            ATM atm = new ATM("ComputerThanyaburi Bank");

            System.out.println("Step 2. Enter Detail of each account.");
            for (int i = 0; i < numberOfAccounts; i++) {
                System.out.println("No." + (i + 1));

                System.out.print("Account ID = ");
                String accountNumber = scanner.nextLine();
                System.out.print("Account Name = ");
                String accountName = scanner.nextLine();
                System.out.print("Password = ");
                String password = scanner.nextLine();
                System.out.print("Balance = ");
                double balance = scanner.nextDouble();
                scanner.nextLine();

                BankAccount newAccount = new BankAccount(accountNumber, accountName, balance, password);
                atm.addAccount(newAccount);
                System.out.println();
            }

            //วนลูปให้ผู้ใช้กรอกรหัสบัญชีและรหัสผ่าน
            while (true) {
                System.out.println("ATM " + atm.getName());
                System.out.print("Account ID : ");
                String accountNumberToCheck = scanner.nextLine();
                System.out.print("Account Password : ");
                String passwordToCheck = scanner.nextLine();

                try {
                    BankAccount account = atm.authenticate(accountNumberToCheck, passwordToCheck);

                    while (true) {
                        System.out.println("\nMenu Service");
                        System.out.println("1. Account Balance");
                        System.out.println("2. Withdrawal");
                        System.out.println("3. Exit");
                        System.out.print("Choose : ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("Name "+account.getAccountName()+" Account Balance: " + account.getBalance());
                                break;
                            case 2:
                                break;
                            case 3:
                                System.out.println("Logging out...");
                                break;
                            default:
                                System.out.println("Please choose a valid option.");
                        }

                        if (choice == 3) {
                            break;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Incorrect account ID or password. Please try again.");
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
