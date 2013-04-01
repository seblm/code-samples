package neon29200;

public class Ingredient {

    protected String nom_ingredient;
    protected double prix_unitaire;

    Ingredient(String nom_ingredient, double prix_unitaire, Ingredients stock){
        System.out.println("Un nouvel ingrédient vient d'être ajouté au stock!");
        this.nom_ingredient=nom_ingredient;
        this.prix_unitaire=prix_unitaire;
    }

    public String DecrisIngredient(){
        return "L'ingrédient "+this.nom_ingredient+" est au prix unitaire de "+this.prix_unitaire+" €";
    }
}