package neon29200;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static java.util.logging.Level.OFF;
import static java.util.logging.Logger.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

public class IngredientsTest {

    @BeforeClass
    public static void shutdownLoggers() {
        getLogger(Ingredient.class.getName()).setLevel(OFF);
        getLogger(Produit_simple.class.getName()).setLevel(OFF);
        getLogger(Produit_compose.class.getName()).setLevel(OFF);
    }

    @Test
    public void should_create_new_ingredient() {
        List<Ingredient> tomates = Ingredients.createIngredients("tomate", 2.5, 10);
        assertThat(tomates).hasSize(10);
    }

    @Test
    public void should_not_create_new_ingredient_outside_of_package() {
        // new Ingredient("tomate", 2.5);
    }

    @Test
    public void should_create_produit_compose_with_ingredients() {
        List<Ingredient> ingredients = Ingredients.createIngredients("cornichons", .3, 2);
        ingredients.addAll(Ingredients.createIngredients("tomate", .5, 1));
        ingredients.addAll(Ingredients.createIngredients("steack", 1, 1));
        Produit bigMac = ProduitsEnVente.createProduits("Big Mac", 4.5, ingredients.toArray(new Ingredient[ingredients.size()]));
    }

}
