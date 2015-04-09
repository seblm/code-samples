package fastfood;

class NoMoreIngredientException extends Exception {

    NoMoreIngredientException(Ingredient ingredient, String productName) {
        super("no more \"" + ingredient + "\" in stock to cook a " + productName);
    }

}
