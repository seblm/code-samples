package neon29200;

import java.util.ArrayList;
import java.util.List;

/**
 * Référence et créée de nouveaux produits.
 */
public final class ProduitsEnVente {

    /**
     * Singleton de ProduitsEnVente utilisé pour référencer de manière unique
     * les produits créés par le système.
     */
    private static ProduitsEnVente produits = new ProduitsEnVente();

    /**
     * Liste des produits créés par ProduitsEnVente.
     */
    private List<Produit> products;

    /**
     * Constructeur privé qui permet à la classe de ne pas pouvoir être
     * instanciée.
     */
    private ProduitsEnVente() {
        products = new ArrayList<Produit>();
    }

    /**
     * Crée une liste de Produit_simple.
     * @param productName nom des produits à créer
     * @param price prix des produits
     * @param quantité nom de Produit_simple à créer
     */
    static List<Produit> createProduits(String productName, double price, int quantity) {
        List<Produit> newProducts = new ArrayList<Produit>();
        for (int i = 0; i < quantity; i++) {
            newProducts.add(new Produit_simple(productName, price, produits));
        }
        produits.products.addAll(newProducts);
        return newProducts;
    }

    /**
     * Crée un nouveau produit.
     * @param productName nom des produits à créer
     * @param price prix des produits
     * @param ingredients liste des ingrédients à créer. Si la liste est vide, le
     * produit sera une instance de Produit_simple. Sinon il s'agira d'un
     * Produit_compose.
     * @return nouveau produit créé.
     */
    static Produit createProduits(String productName, double price, Ingredient... ingredients) {
        Produit product;
        if (ingredients.length > 0) {
            product = new Produit_compose(productName, price, produits, ingredients);
        } else {
            product = new Produit_simple(productName, price, produits);
        }
        produits.products.add(product);
        return product;
    }
}