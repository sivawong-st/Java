package OOSE_H2;

public class Manager extends Person {
    private String loginId;
    private String password;

    public Manager(String cardId, String name, String gender, String loginId, String password) {
        super(cardId, name, gender); 
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Manager Login ID: " + loginId);
    }

    public void manageAccounts(ATM atm) {
        System.out.println("Managing accounts...");
    }
}
