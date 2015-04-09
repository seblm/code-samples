package fastfood;

import org.junit.Test;

import static fastfood.Ingredient.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class FastFoodTest {

    @Test
    public void should_make_bigmac() throws Exception {
        FastFood stock = new FastFood();
        stock.add(tomate, 3); // ajout de 3 tomates
        stock.add(steack, 2); // ajout de 2 steacks
        stock.add(cornichons, 5); // ajout de 5 cornichons
        ProduitCompose bigMac = stock.createBigMac();
        assertThat(stock.get(tomate)).isEqualTo(1);
        assertThat(stock.get(steack)).isEqualTo(1); // ?
        assertThat(stock.get(cornichons)).isEqualTo(3); // ?
        try {
            ProduitCompose nouveauBigMac = stock.createBigMac();
            fail();
        } catch (NoMoreIngredientException e) {
            assertThat(e.getMessage()).isEqualTo("no more \"tomate\" in stock to cook a BigMac");
        }
    }

}
