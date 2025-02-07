import java.util.Scanner;

import model.Account;
import model.Card;
import state.AtmContext;

public class ATM {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Account account = new Account(1000.50, "Abhijeet");
        Card card = new Card(account);
        AtmContext atmContext = new AtmContext(card, sc);
        while (true) {
            System.out.println("Press 1 to continue or any other number to cancel \n");
            int inputValue = sc.nextInt();
            if (inputValue == 1) {
                atmContext.execute(false);
            } else {
                atmContext.execute(true);
                break;
            }
        }
        sc.close();
    }
}
