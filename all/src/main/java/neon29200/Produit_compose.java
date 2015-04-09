package neon29200;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Produit_compose extends Produit {

    private static final Logger LOGGER = getLogger(Produit_compose.class.getName());

    protected List<Ingredient> liste_ingredient;

    Produit_compose(String nom_produit, double prix_produit, ProduitsEnVente stock, Ingredient... liste_ingredient) {
        super(nom_produit, prix_produit, stock);
        LOGGER.info("Un nouveau produit composé est disponible!");
        this.liste_ingredient = Arrays.asList(liste_ingredient);
    }

}
