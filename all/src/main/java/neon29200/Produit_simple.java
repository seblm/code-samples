package neon29200;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Produit_simple extends Produit {

    private static final Logger LOGGER = getLogger(Produit_simple.class.getName());

    public Produit_simple(String nom_produit, double prix_produit, ProduitsEnVente stock) {
        super(nom_produit, prix_produit, stock);
        LOGGER.info("Un nouveau produit simple est disponible!");
    }

}