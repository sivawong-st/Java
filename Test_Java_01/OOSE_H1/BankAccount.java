package OOSE_H1;

public class BankAccount {
    private String accountNumber;
    private String accountName;
    private double balance;
    private String password;

    // Constructor
    public BankAccount(String accountNumber, String accountName, double balance, String password) {
        if (accountNumber.length() != 13) {
            throw new IllegalArgumentException("Account ID must be 13 digits");
        }
        if (accountName.length() > 50) {
            throw new IllegalArgumentException("AccountName <= 50 alphabet !!!");
        }
        if (balance < 0 || balance > 1000000) {
            throw new IllegalArgumentException("balance > 1000000 ฿ !!!");
        }
        if (password.length() != 4 || !password.matches("\\d{4}")) {
            throw new IllegalArgumentException("Password 4 digits !!!");
        }

        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.password = password;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAccountName() {
        return this.accountName;
    }
}
