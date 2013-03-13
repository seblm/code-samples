package fastfood;

import java.util.HashMap;
import java.util.Map;

import static fastfood.Ingredient.cornichons;
import static fastfood.Ingredient.steack;
import static fastfood.Ingredient.tomate;

class FastFood {

    private final Map<Ingredient,Integer> ingredients;

    FastFood() {
        ingredients = new HashMap<Ingredient, Integer>();
    }

    void add(Ingredient ingredient, int quantity) {
        Integer previousQuantity = 0;
        if (ingredients.containsKey(ingredient)) {
            previousQuantity = ingredients.get(ingredient);
        }
        ingredients.put(ingredient, previousQuantity + quantity);
    }

    ProduitCompose createBigMac() throws NoMoreIngredientException {
        checkIngredient(tomate, 2);
        checkIngredient(steack, 1);
        checkIngredient(cornichons, 2);

        decreaseQuantityForIngredient(tomate, 2);
        decreaseQuantityForIngredient(steack, 1);
        decreaseQuantityForIngredient(cornichons, 2);

        return new ProduitCompose().add(tomate, 2).add(steack).add(cornichons, 2);
    }

    Integer get(Ingredient ingredient) {
        return ingredients.get(ingredient);
    }

    private void decreaseQuantityForIngredient(Ingredient ingredient, int quantity) {
        ingredients.put(ingredient, ingredients.get(ingredient) - quantity);
    }

    private void checkIngredient(Ingredient ingredient, int requiredQuantity) throws NoMoreIngredientException {
        if (!ingredients.containsKey(ingredient) || ingredients.get(ingredient) < requiredQuantity) {
            throw new NoMoreIngredientException(ingredient, "BigMac");
        }
    }
}
