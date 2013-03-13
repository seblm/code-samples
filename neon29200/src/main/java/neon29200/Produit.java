package neon29200;

public abstract class Produit {

    protected String nom_produit;
    protected double prix_produit;

    protected Produit(String nom_produit, double prix_produit, ProduitsEnVente stock) {
        if (stock == null) {
            throw new IllegalArgumentException("stock cannot be null");
        }
        this.nom_produit=nom_produit;
        this.prix_produit=prix_produit;
    }
}