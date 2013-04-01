package neon29200;

public class Produit_simple extends Produit {

    public Produit_simple(String nom_produit, double prix_produit, ProduitsEnVente stock) {
        super(nom_produit, prix_produit, stock);
        System.out.println("Un nouveau produit simple est disponible!");
    }

}