package neon29200;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet de créer de nouveaux ingrédients.
 */
public final class Ingredients {

    /**
     * Singleton statique qui permet de ne référencer qu'une seule instance
     * de la classe Ingredients.
     */
    private static Ingredients ingredients = new Ingredients();

    /**
     * Liste de tous les ingrédients créés par l'intermédiaire de l'instance
     * unique d'ingrédients.
     */
    private List<Ingredient> stockIngredient;

    /**
     * Constructeur privé interdisant son instanciation à l'extérieur de la classe.
     */
    private Ingredients() {
        this.stockIngredient = new ArrayList<Ingredient>();
    }

    /**
     * Crée une nouvelle liste d'ingrédients.
     * @param ingredientType nom des ingrédients
     * @param price prix des ingrédients créés
     * @param quantity nombre d'ingrédients à créer
     * @return liste de tous les ingrédients créés
     */
    public static List<Ingredient> createIngredients(String ingredientType, double price, int quantity) {
        List<Ingredient> newIngredients = new ArrayList<Ingredient>();
        for (int i = 0; i < quantity; i++) {
            newIngredients.add(new Ingredient(ingredientType, price, ingredients));
        }
        ingredients.stockIngredient.addAll(newIngredients);
        return newIngredients;
    }
}