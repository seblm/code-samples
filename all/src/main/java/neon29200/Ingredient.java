package neon29200;

import java.util.logging.Logger;

public class Ingredient {

    private static final Logger logger = Logger.getLogger(Ingredient.class.getName());
    
    protected String nom_ingredient;
    protected double prix_unitaire;

    Ingredient(String nom_ingredient, double prix_unitaire, Ingredients stock){
        logger.info("Un nouvel ingrédient vient d'être ajouté au stock!");
        this.nom_ingredient=nom_ingredient;
        this.prix_unitaire=prix_unitaire;
    }

    public String DecrisIngredient(){
        return "L'ingrédient "+this.nom_ingredient+" est au prix unitaire de "+this.prix_unitaire+" €";
    }
}