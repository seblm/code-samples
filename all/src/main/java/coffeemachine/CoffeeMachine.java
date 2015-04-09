package coffeemachine;

import java.util.Map;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

public class CoffeeMachine {

    private final DrinkMaker drinkMaker;
    private final EmailNotifier emailNotifier;
    private final BeverageQuantityChecker beverageQuantityChecker;
    private final Map<Drink, Integer> drinks;

    private int coins;

    public CoffeeMachine(DrinkMaker drinkMaker, EmailNotifier emailNotifier, BeverageQuantityChecker beverageQuantityChecker) {
        this.drinkMaker = drinkMaker;
        this.emailNotifier = emailNotifier;
        this.beverageQuantityChecker = beverageQuantityChecker;
        this.coins = 0;
        this.drinks = stream(Drink.values()).collect(toMap(drink -> drink, drink -> 0));
    }

    public CoffeeMachine order(Order order) {
        int missing = order.drink.cost - coins;
        this.coins = 0;
        
        if (missing > 0) {
            drinkMaker.command("M:missing " + missing + " cents");
            return this;
        }
        
        if (beverageQuantityChecker.isEmpty(order.drink)) {
            drinkMaker.command("M:No more " + order.drink);
            emailNotifier.notifyMissingDrink(order.drink);
            return this;
        }
        
        drinks.merge(order.drink, 1, (left, right) -> left + right);
        drinkMaker.command(order.toDrinkMakerProtocol());
        drinkMaker.command(order.toDrinkMakerProtocolMessage());
        return this;
    }

    public CoffeeMachine insert(int coins) {
        this.coins += coins;
        return this;
    }

    public String report() {
        Set<Map.Entry<Drink, Integer>> entries = drinks.entrySet();
        return entries.stream()
                .sorted((left, right) -> left.getKey().name.compareTo(right.getKey().name))
                .map(entry -> entry.getKey() + " " + entry.getValue())
                .collect(joining("\n"))
                +
                "\ntotal " + entries.stream().mapToInt(entry -> entry.getKey().cost * entry.getValue()).sum();
    }
}
