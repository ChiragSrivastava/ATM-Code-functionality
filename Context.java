package state;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import enums.StateEnum;
import model.Card;

public class AtmContext {
    Map<Class<? extends State>, State> cache; // to store the state objects for a particular user,
                                              // no need to create states everytime the user is in that step
    StateEnum[] allStates;
    int currentState, totalStates;
    State state;
    Card card;
    Scanner sc;

    // create a new ATM context
    public AtmContext(Card card, Scanner sc) {
        this.card = card;
        cache = new HashMap<>();
        allStates = StateEnum.values();
        currentState = -1;
        totalStates = allStates.length;
        this.sc = sc;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void execute(boolean cancel) {
        if (cancel) {
            currentState = totalStates - 1;
        } else {
            // set the step to next one
            currentState++;
            // if we are at the last state, set it to 0 i.e welcome state
            if (currentState == totalStates) {
                currentState = 0;
            }
        }
        handleFlow(allStates[currentState]);
    }

    public void handleFlow(StateEnum eStateEnum) { // passing the current state we are in and executing respective
                                                   // state's handle flow
        switch (eStateEnum) {
            case WELCOME -> {
                if (!cache.containsKey(WelcomeState.class)) {
                    cache.put(WelcomeState.class, new WelcomeState());
                }
                setState(cache.get(WelcomeState.class));
            }
            case INSERT_CARD -> {
                if (!cache.containsKey(InsertCardState.class)) {
                    cache.put(InsertCardState.class, new InsertCardState());
                }
                setState(cache.get(InsertCardState.class));
            }
            case TRANSACTION -> {
                if (!cache.containsKey(TransactionState.class)) {
                    cache.put(TransactionState.class, new TransactionState(card, sc));
                }
                setState(cache.get(TransactionState.class));
            }
            case REMOVE_CARD -> {
                if (!cache.containsKey(RemoveCardState.class)) {
                    cache.put(RemoveCardState.class, new RemoveCardState());
                }
                setState(cache.get(RemoveCardState.class));
            }
            case THANK_YOU -> {
                if (!cache.containsKey(EndState.class)) {
                    cache.put(EndState.class, new EndState());
                }
                setState(cache.get(EndState.class));
            }
        }
        this.state.handleFlow();
    }
}
