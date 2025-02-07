package model;

public class Account {
    private double balance;
    private String userName; // for now only having name of the account's owner, we can also have a seperate
                             // model User and have several properties like name, dob, address etc.

    public Account(double balance, String userName) {
        this.balance = balance;
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
package model;

public class Card {
    Account account;

    public Card(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
