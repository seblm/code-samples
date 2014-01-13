package coffeemachine;

enum Drink {
    TEA('T', .4), CHOCOLATE('H', .5), COFFEE('C', .6), ORANGE_JUICE('O', .5);

    final double cost;

    private final char identifier;

    Drink(char identifier, double cost) {
        this.identifier = identifier;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.valueOf(identifier);
    }
}
