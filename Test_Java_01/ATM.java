import java.util.ArrayList;

public class ATM {
    private ArrayList<BankAccount> accounts;
    private String name;

    // Constructor
    public ATM(String name) {
        this.name = name;
        accounts = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public BankAccount authenticate(String accountNumber, String password) { 
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getPassword().equals(password)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Incorrect account ID or password.");
    }
}
