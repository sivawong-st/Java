package OOSE_H2;

public interface ATMAction {
    public boolean withdrawable(Account account, double amount);
    public void depositable(Account account, double amount);
    public boolean transferable(Account sender, Account receiver, double amount);
    public void checkBalanceable(Account account);
}

