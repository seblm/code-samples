- Ma classe Produit ne devrait-elle pas être déclarée abstract car je n'aurai pas d'objet Produit mais seulement des
  objets Produit_compose et Produit_simple?
  Oui c'est effectivement intéressant dans ce cas de passer la classe Produit abstract.
- Je ne peux pas faire this.liste_ingredient=liste_ingredient dans mon constructeur c'est pas comme si c'était des int
  ou autres.
  Ce que tu peux faire c'est transformer la propriété liste_ingredient de ArrayList<Ingredient> en List<Ingredient>
  c'est d'ailleurs une bonne pratique de déclarer les types des variables sous leur forme la plus abstraite et ainsi de
  pouvoir affecter la liste dans le constructeur avec cet appel :
  this.liste_ingredient = Arrays.asList(liste_ingredient);
- J'aimerai qu'à chaque création d'un objet Ingredient il soit ajouté à une liste stock_ingredient.
  Si tel est ton besoin alors je te propose l'utilisation de la classe statique Ingredients :
  List<Ingredient> tomates = Ingredients.createIngredients("tomate", 2.5, 10);
  // augmenter le stock de tomates (anciennement tomate.setQuantiteIngredient(10))
  tomates.addAll(Ingredient.createIngredients("tomate", 2.5, 50));
- Quand on écrit:
   ProduitCompose bigMac = new ProduitCompose("Big Mac", 4.5,
    new Ingredient("cornichon", .3, 2),
    new Ingredient("tomate", .5, 1),
    new Ingredient("steack", 1, 1));
  On peut crée un nouvel objet Ingredient lorsque l'on crée l'objet Produit_compose (comme ci-dessus) mais je veux que
  les objets Ingredient puissent être appelé seulement s'il ont été crées au préalable (c'est-à-dire qu'ils sont dans
  stock_ingredient)
  Avec l'objet static Ingredients, ce code se transforme en :
  List<Ingredient> ingredients = Ingredients.createIngredients("cornichons", .3, 2);
  ingredients.addAll(Ingredients.createIngredients("tomate", .5, 1));
  ingredients.addAll(Ingredients.createIngredients("steack", 1, 1));
  Produit_compose bigMac = new Produit_compose("Big Mac", 4.5, ingredients.toArray(new Ingredient[ingredients.size()]));
- J'aimerai qu'à chaque création d'un objet Produit_simple ou Produit-compose il soit ajouté à une liste
  produits_en_vente.
  Ici ça peut être la même technique que pour Ingredients.

