package neon29200;

import java.util.Arrays;
import java.util.List;

public class Produit_compose extends Produit {

    protected List<Ingredient> liste_ingredient;

    Produit_compose(String nom_produit, double prix_produit, ProduitsEnVente stock, Ingredient... liste_ingredient) {
        super(nom_produit, prix_produit, stock);
        System.out.println("Un nouveau produit composé est disponible!");
        this.liste_ingredient = Arrays.asList(liste_ingredient);
    }

}
