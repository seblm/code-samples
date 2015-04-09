package coffeemachine;

public class Order {
    final Drink drink;
    final int sugars;
    
    boolean extraHot;

    public Order(Drink drink, int sugars) {
        this.drink = drink;
        this.sugars = sugars;
        this.extraHot = false;
    }

    public Order(Drink drink) {
        this(drink, 0);
    }

    String toDrinkMakerProtocol() {
        return drink.id + (extraHot ? "h" : "") + ":" + (hasSugars() ? sugars : "") + ":" + (hasSugars() ? "0" : "");
    }

    String toDrinkMakerProtocolMessage() {
        return "" +
                "M:order " + (extraHot ? "extra hot " : "") + drink +
                " with" + (hasSugars() ? " " + sugars : "out") + " sugar" + (hasMoreThatOneSugars() ? "s" : "") +
                (hasSugars() ? " and a stick" : "");
    }

    boolean hasSugars() {
        return sugars > 0;
    }

    public boolean hasMoreThatOneSugars() {
        return sugars > 1;
    }

    public Order extraHot() {
        this.extraHot = true;
        return this;
    }
}
