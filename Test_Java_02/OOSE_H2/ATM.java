package OOSE_H2;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM implements ATMAction {
    private ArrayList<Account> accounts;
    private String name;
    private Manager managerAccount;

    public ATM(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.managerAccount = null;
    }

    public String getName() {
        return this.name;
    }

    public void setManagerAccount(Manager manager) {
        if (managerAccount == null) {
            managerAccount = manager;
            System.out.println("Manager account has been set.");
        } else {
            System.out.println("Manager account is already set.");
        }
    }

    public Manager authenticateManager(String loginId, String password) {
        if (managerAccount != null && managerAccount.getLoginId().equals(loginId) && managerAccount.getPassword().equals(password)) {
            return managerAccount;
        }
        throw new IllegalArgumentException("Incorrect Manager login credentials.");
    }

    public Account authenticateUser(String accountId, String password) {
        for (Account account : accounts) {
            if (account.getLoginId().equals(accountId) && account.getPassword().equals(password)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Incorrect account ID or password.");
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Implement ATMAction methods
    @Override
    public boolean withdrawable(Account account, double amount) {
        if (amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public void depositable(Account account, double amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
        } else {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
    }

    @Override
    public boolean transferable(Account sender, Account receiver, double amount) {
        System.out.print("Sender Name -> "+sender.getName()+" to "+"Receiver Name -> "+receiver.getName());
        System.out.println();
        if (withdrawable(sender, amount)) {
            depositable(receiver, amount);
            return true;
        }
        return false;
    }

    @Override
    public void checkBalanceable(Account account) {
        System.out.println(" Balance: " + account.getBalance());
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM("ComputerThanyaburi Bank");

        // Manager setup and operations
        if (atm.managerAccount == null) {
            System.out.println("No manager account is set. Please set a manager.");

            System.out.print("Enter Manager Card ID: ");
            String managerCardId = scanner.nextLine();
            System.out.print("Manager Name = ");
            String managerName = scanner.nextLine();
            System.out.print("Gender(male/female) = ");
            String managerGender = scanner.nextLine();
            System.out.print("Enter Manager Account ID: ");
            String managerId = scanner.nextLine();
            System.out.print("Enter Manager Password: ");
            String managerPassword = scanner.nextLine();

            atm.setManagerAccount(new Manager(managerCardId, managerName, managerGender, managerId, managerPassword));
        }

        System.out.println("\nManager Login");
        boolean managerLoggedIn = false;
        while (!managerLoggedIn) {
            System.out.print("Enter Manager Account ID: ");
            String managerLoginId = scanner.nextLine();
            System.out.print("Enter Manager Password: ");
            String managerLoginPassword = scanner.nextLine();

            try {
                Manager manager = atm.authenticateManager(managerLoginId, managerLoginPassword);
                if (manager != null) {
                    managerLoggedIn = true;
                    System.out.println("Manager logged in successfully.");

                    // Manager menu
                    while (true) {
                        System.out.println("\nManager Menu");
                        System.out.println("1.Manage Accounts");
                        System.out.println("2.Exit");
                        System.out.print("Choose: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:

                            System.out.print("Step 1. Enter amount of all accounts = ");
                            int numberOfAccounts = scanner.nextInt();

                            while (numberOfAccounts < 5) {
                                System.out.println("The number of accounts must be at least 5. Please enter again.");
                                System.out.print("Step 1. Enter amount of all accounts = ");
                                numberOfAccounts = scanner.nextInt();
                            }

                            scanner.nextLine();

                            System.out.println("Step 2. Enter Detail of each account.\n");
                            for (int i = 0; i < numberOfAccounts; i++) {
                                System.out.println("No." + (i + 1));

                                System.out.print("Card ID = ");
                                String cardId = scanner.nextLine();
                                System.out.print("Account Name = ");
                                String accountName = scanner.nextLine();
                                System.out.print("Gender(male/female) = ");
                                String gender = scanner.nextLine();
                                System.out.print("Account ID = ");
                                String loginId = scanner.nextLine();
                                System.out.print("Password = ");
                                String password = scanner.nextLine();
                                System.out.print("Balance = ");
                                double balance = scanner.nextDouble();
                                scanner.nextLine();

                                Account newAccount = new Account(cardId,accountName,gender,loginId,password,balance);
                                atm.addAccount(newAccount);
                                System.out.println();
                            }

                            System.out.println("-----------------------------------------------------------------------");

                            while (true) {
                                System.out.println("ATM " + atm.getName());
                                            System.out.print("Enter Account ID: ");
                                            String accountId = scanner.nextLine();
                                            System.out.print("Enter Account Password: ");
                                            String password = scanner.nextLine();
                                
                                            try {
                                                Account account = atm.authenticateUser(accountId, password);
                                                while (true) {
                                                    System.out.println("\nAccount Menu");
                                                    System.out.println("1.Check Balance");
                                                    System.out.println("2.Withdraw Money");
                                                    System.out.println("3.Deposit Money");
                                                    System.out.println("4.Transfer Money");
                                                    System.out.println("5.Exit");
                                                    System.out.print("Choose an option: ");
                                                    int option = scanner.nextInt();
                                                    scanner.nextLine();

                                                    System.out.println("-----------------------------------------------------------------------");

                                                    switch (option) {
                                                        case 1:
                                                            System.out.print("Name -> "+account.getName());
                                                            atm.checkBalanceable(account);
                                                            break;
                                                        case 2:
                                                            System.out.print("Enter amount to withdraw: ");
                                                            double withdrawAmount = scanner.nextDouble();
                                                            scanner.nextLine();
                                                            if (atm.withdrawable(account, withdrawAmount)) {
                                                                System.out.println("Withdrawing money");
                                                                System.out.print("Name -> "+account.getName());
                                                                atm.checkBalanceable(account);
                                                                System.out.println("Withdrawal successful.");
                                                            } else {
                                                                System.out.println("Insufficient balance or invalid amount.");
                                                            }
                                                            break;
                                                        case 3:
                                                            System.out.print("Enter amount to deposit: ");
                                                            double depositAmount = scanner.nextDouble();
                                                            scanner.nextLine();
                                                            atm.depositable(account, depositAmount);
                                                            System.out.println("Deposit money into the account");
                                                            System.out.print("Name -> "+account.getName());
                                                            atm.checkBalanceable(account);
                                                            System.out.println("Deposit successful.");
                                                            break;
                                                        case 4:
                                                            System.out.print("Enter Account ID of the recipient: ");
                                                            String recipientAccountId = scanner.nextLine();
                                                            Account receiver = null;
                                                            for (Account acc : atm.accounts) {
                                                                if (acc.getLoginId().equals(recipientAccountId)) {
                                                                    receiver = acc;
                                                                    break;
                                                                }
                                                            }
                                                            if (receiver != null) {
                                                                System.out.print("Enter amount to transfer: ");
                                                                double transferAmount = scanner.nextDouble();
                                                                scanner.nextLine();
                                                                if (atm.transferable(account, receiver, transferAmount)) {
                                                                    System.out.println("Transfer successful.");
                                                                } else {
                                                                    System.out.println("Transfer failed due to insufficient balance or invalid amount.");
                                                                }
                                                            } else {
                                                                System.out.println("Recipient account not found.");
                                                            }
                                                            break;
                                                        case 5:
                                                            System.out.println("Logging out...");
                                                            break;
                                                        default:
                                                            System.out.println("Invalid option. Please try again.");
                                                    }

                                                    System.out.println("-----------------------------------------------------------------------");

                                                    if (option == 5) {
                                                        break;
                                                    }

                                                }
                                            } catch (IllegalArgumentException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }

                            case 2:
                                System.out.println("Exiting Manager Menu...");
                                return;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
