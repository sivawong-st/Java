package OOSE_H2;

public class Account extends Person {
    private String loginId;
    private String password;
    private double balance;


    public Account(String cardId, String name, String gender, String loginId, String password, double balance) {
        super(cardId, name, gender);

        if (password.length() != 4 || !password.matches("\\d{4}")) {
            throw new IllegalArgumentException("Password must be 4 digits.");
        }

        if (balance < 0 || balance > 1_000_000) {
            throw new IllegalArgumentException("Balance must be between 0 and 1,000,000 ฿.");
        }

        this.loginId = loginId;
        this.password = password;
        this.balance = balance;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0 || balance > 1_000_000) {
            throw new IllegalArgumentException("Balance must be between 0 and 1,000,000 ฿.");
        }
        this.balance = balance;
    }

    public void displayDetails() {
        System.out.println("Card ID: " + getCardId());
        System.out.println("Name: " + getName());
        System.out.println("Gender: " + getGender());
        System.out.println("Login ID: " + loginId);
        System.out.println("Balance: " + balance);
    }
}
