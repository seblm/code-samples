package coffeemachine;

public enum Drink {
    TEA('T', "tea", 40),
    CHOCOLATE('H', "chocolate", 50),
    COFFEE('C', "coffee", 60),
    ORANGE_JUICE('O', "orange juice", 60);
    
    final char id;
    final int cost;
    final String name;

    Drink(char id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
