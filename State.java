package state;

public interface State {
    void handleFlow();
}
package state;

public class WelcomeState implements State {

    @Override
    public void handleFlow() {
        System.out.println("Hello! Welcome to XYZ atm");
    }

}
package state;

public class InsertCardState implements State {

    @Override
    public void handleFlow() {
        System.out.println("Insert your card");
    }

}
package state;

import java.util.Scanner;

import model.Card;

public class TransactionState implements State {

    Card card;
    Scanner sc;

    public TransactionState(Card card, Scanner sc) {
        this.card = card;
        this.sc = sc;
    }

    @Override
    public void handleFlow() {
        System.out.println("Press 1 to withdraw or 2 to deposit");
        int keyPress = sc.nextInt();
        System.out.println("Enter amount");
        double amount = sc.nextDouble();
        double accountBalance = card.getAccount().getBalance();
        if (keyPress == 1) {
            if (accountBalance < amount)
                System.out.println("Insufficient funds!");
            else
                card.getAccount().setBalance(accountBalance - amount);
        } else if (keyPress == 2) {
            card.getAccount().setBalance(accountBalance + amount);
        }
        System.out.println("Your current balance : " + card.getAccount().getBalance());
    }

}
package state;

public class RemoveCardState implements State {

    @Override
    public void handleFlow() {
        System.out.println("Please remove your card");
    }

}
package state;

public class EndState implements State {

    @Override
    public void handleFlow() {
        System.out.println("Thank you!");
    }

}
