package coffeemachine;

class Order {
    final Drink drink;
    final double money;
    final boolean extraHot;
    final int sugars;

    Order(Drink drink, double money, boolean extraHot) {
        this.drink = drink;
        this.money = money;
        this.extraHot = extraHot;
        this.sugars = 0;
    }

    Order(Drink drink, double money, boolean extraHot, int sugars) {
        this.drink = drink;
        this.money = money;
        this.extraHot = extraHot;
        this.sugars = sugars;
    }
}