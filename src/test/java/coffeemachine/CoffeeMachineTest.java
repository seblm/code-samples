package coffeemachine;

import org.assertj.core.api.StringAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {

    @Spy
    private DrinkMaker drinkMaker = new DrinkMaker();

    @Test
    public void should_send_instruction_to_drink_maker_to_makes_tea_with_one_sugar_and_one_stick() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.TEA, .6, false, 1));

        assertThatCommand().isEqualTo("T:1:0");
    }

    @Test
    public void should_send_instruction_to_drink_maker_to_makes_chocolate_without_stick() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.CHOCOLATE, .6, false));

        assertThatCommand().isEqualTo("H::");
    }

    @Test
    public void should_send_instruction_to_drink_maker_to_makes_coffee_with_two_sugars_and_one_stick() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.COFFEE, .6, false, 2));

        assertThatCommand().isEqualTo("C:2:0");
    }

    @Test
    public void should_send_instruction_to_drink_maker_to_makes_extra_hot_tea_with_one_sugar_and_one_stick() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.TEA, .6, true, 2));

        assertThatCommand().isEqualTo("Th:2:0");
    }

    @Test
    public void should_send_instruction_to_drink_maker_to_makes_extra_hot_chocolate_without_stick() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.CHOCOLATE, .6, true, 1));

        assertThatCommand().isEqualTo("Hh:1:0");
    }

    @Test
    public void should_send_instruction_to_drink_maker_to_makes_extra_hot_coffee_with_two_sugars_and_one_stick() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.COFFEE, .6, true));

        assertThatCommand().isEqualTo("Ch::");
    }

    @Test
    public void should_send_instruction_to_drink_maker_to_makes_orange_juice() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.ORANGE_JUICE, .6, false));

        assertThatCommand().isEqualTo("O::");
    }

    @Test
    public void should_send_instruction_to_drink_maker_for_customer_to_see() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.displayMessage("message-content");

        assertThatCommand().isEqualTo("M:message-content");
    }

    @Test
    public void should_not_make_tea_if_not_enough_money_is_given() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.TEA, .3, false));

        assertThatCommand().startsWith("M:").contains("0.1");
    }

    @Test
    public void should_not_make_chocolate_if_not_enough_money_is_given() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.CHOCOLATE, .3, false));

        assertThatCommand().startsWith("M:").contains("0.2");
    }

    @Test
    public void should_not_make_coffee_if_not_enough_money_is_given() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.COFFEE, .3, false));

        assertThatCommand().startsWith("M:").contains("0.3");
    }

    @Test
    public void should_not_make_orange_juice_if_not_enough_money_is_given() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.serves(new Order(Drink.COFFEE, .5, false));

        assertThatCommand().startsWith("M:").contains("0.1");
    }

    @Test
    public void should_generates_report_with_coffee() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        coffeeMachine.serves(new Order(Drink.COFFEE, .6, false));

        String report = coffeeMachine.report();

        assertThat(report.split("\n")).contains("coffee: 1");
    }

    @Test
    public void should_generates_report_with_coffee_and_tea() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        coffeeMachine.serves(new Order(Drink.COFFEE, .6, false));

        String report = coffeeMachine.report();

        assertThat(report.split("\n")).contains("tea: 0", "coffee: 1");
    }

    @Test
    public void should_generates_report_with_all_drinks_and_money() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        coffeeMachine.serves(new Order(Drink.COFFEE, .6, false));
        coffeeMachine.serves(new Order(Drink.TEA, .6, false));
        coffeeMachine.serves(new Order(Drink.TEA, .1, false));
        coffeeMachine.serves(new Order(Drink.COFFEE, .6, true));

        String report = coffeeMachine.report();

        assertThat(report.split("\n")).containsOnly("coffee: 2", "tea: 1", "chocolate: 0", "orange_juice: 0", "money earned: 1.6");
    }

    @Test
    public void should_warn_if_shortage() {
        CoffeeMachine coffeeMachine = spy(new CoffeeMachine(drinkMaker));
        when(coffeeMachine.isEmpty(eq(Drink.COFFEE))).thenReturn(true);

        coffeeMachine.serves(new Order(Drink.COFFEE, .6, false));

        verify(coffeeMachine).notifyMissingDrink(eq(Drink.COFFEE));
        assertThatCommand().isEqualTo("M:no more coffee");
    }

    private StringAssert assertThatCommand() {
        ArgumentCaptor<String> command = ArgumentCaptor.forClass(String.class);
        verify(drinkMaker).receive(command.capture());
        return assertThat(command.getValue());
    }
}
