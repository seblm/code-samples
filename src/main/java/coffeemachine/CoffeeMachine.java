package coffeemachine;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine implements BeverageQuantityChecker, EmailNotifier {
    private DrinkMaker drinkMaker;
    private Map<Drink, Integer> drinksServed;

    public CoffeeMachine(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
        this.drinksServed = new HashMap<>();
        for (Drink drink : Drink.values()) {
            drinksServed.put(drink, 0);
        }
    }

    public void serves(Order order) {
        if (order.money < order.drink.cost) {
            displayMessage("please add " + (order.drink.cost * 10 - order.money * 10) / 10);
        } else if (isEmpty(order.drink)) {
            displayMessage("no more " + order.drink.name().toLowerCase());
            notifyMissingDrink(order.drink);
        } else {
            drinksServed.put(order.drink, drinksServed.get(order.drink) + 1);
            drinkMaker.receive(toDrinkMakerProtocol(order));
        }
    }

    private String toDrinkMakerProtocol(Order order) {
        StringBuilder drinkMakerProtocol = new StringBuilder();
        drinkMakerProtocol.append(order.drink.toString());
        if (order.extraHot) {
            drinkMakerProtocol.append('h');
        }
        drinkMakerProtocol.append(':');
        if (order.sugars > 0) {
            drinkMakerProtocol.append(order.sugars).append(":0");
        } else {
            drinkMakerProtocol.append(':');
        }
        return drinkMakerProtocol.toString();
    }

    public void displayMessage(String message) {
        drinkMaker.receive("M:" + message);
    }

    public String report() {
        double moneyEarned = 0;
        StringBuilder report = new StringBuilder();
        for (Map.Entry<Drink, Integer> drink : drinksServed.entrySet()) {
            Integer drinkServed = drink.getValue();
            moneyEarned += drink.getKey().cost * drinkServed;
            report.append(drink.getKey().name().toLowerCase()).append(": ").append(drinkServed).append('\n');
        }
        report.append("money earned: ").append(moneyEarned);
        return report.toString();
    }

    @Override
    public void notifyMissingDrink(Drink drink) {
    }

    @Override
    public boolean isEmpty(Drink drink) {
        return false;
    }
}
