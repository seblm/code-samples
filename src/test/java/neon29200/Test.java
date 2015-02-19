package neon29200;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Test {

    @org.junit.Test
    public void main() throws Exception {
        List<Ingredient> tomates = Ingredients.createIngredients("tomate", 2.5, 10);
        assertThat(tomates.get(0).DecrisIngredient()).isEqualTo("L'ingrédient tomate est au prix unitaire de 2.5 €");
        List<Ingredient> steacks = Ingredients.createIngredients("steack", 4, 5);
        assertThat(steacks.get(0).DecrisIngredient()).isEqualTo("L'ingrédient steack est au prix unitaire de 4.0 €");
        tomates.addAll(Ingredients.createIngredients("tomate", 2.5, 50));
        assertThat(tomates.get(0).DecrisIngredient()).isEqualTo("L'ingrédient tomate est au prix unitaire de 2.5 €");
        Produit frite = ProduitsEnVente.createProduits("frite", 4.5, 1).get(0);

        List<Ingredient> ingredients = Ingredients.createIngredients("cornichons", .3, 2);
        ingredients.addAll(Ingredients.createIngredients("tomate", .5, 1));
        ingredients.addAll(Ingredients.createIngredients("steack", 1, 1));
        Produit bigMac = ProduitsEnVente.createProduits("Big Mac", 4.5, ingredients.toArray(new Ingredient[ingredients.size()]));
    }

}