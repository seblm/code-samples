package coffeemachine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static coffeemachine.Drink.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CoffeeMachineTest {
    @Mock
    private DrinkMaker drinkMaker;

    @Mock
    private EmailNotifier emailNotifier;

    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;

    private void thereAreNeverShortage() {
        given(beverageQuantityChecker.isEmpty(any(Drink.class))).willReturn(FALSE);
    }

    @Test
    public void should_send_command_with_tea_one_sugar_and_a_stick() {
        thereAreNeverShortage();
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, emailNotifier, beverageQuantityChecker).insert(40);

        coffeeMachine.order(new Order(TEA, 1));

        verify(drinkMaker).command("T:1:0");
        verify(drinkMaker).command("M:order tea with 1 sugar and a stick");
    }

    @Test
    public void should_send_command_with_chocolate_without_sugar_and_without_a_stick() {
        thereAreNeverShortage();
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, emailNotifier, beverageQuantityChecker).insert(50);

        coffeeMachine.order(new Order(CHOCOLATE));

        verify(drinkMaker).command("H::");
        verify(drinkMaker).command("M:order chocolate without sugar");
    }

    @Test
    public void should_send_command_with_coffee_with_two_sugars_and_a_stick() {
        thereAreNeverShortage();
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, emailNotifier, beverageQuantityChecker).insert(60);

        coffeeMachine.order(new Order(COFFEE, 2));

        verify(drinkMaker).command("C:2:0");
        verify(drinkMaker).command("M:order coffee with 2 sugars and a stick");
    }

    @Test
    public void should_send_command_with_orange_juice() {
        thereAreNeverShortage();
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, emailNotifier, beverageQuantityChecker).insert(60);

        coffeeMachine.order(new Order(ORANGE_JUICE));

        verify(drinkMaker).command("O::");
        verify(drinkMaker).command("M:order orange juice without sugar");
    }

    @Test
    public void should_not_makes_drink_if_not_enough_amout_of_money_is_given() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, emailNotifier, beverageQuantityChecker);

        coffeeMachine.insert(42).order(new Order(CHOCOLATE));

        verify(drinkMaker).command("M:missing 8 cents");
    }

    @Test
    public void should_send_command_with_extra_hot_coffee() {
        thereAreNeverShortage();
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, emailNotifier, beverageQuantityChecker).insert(60);

        coffeeMachine.order(new Order(COFFEE).extraHot());

        verify(drinkMaker).command("Ch::");
        verify(drinkMaker).command("M:order extra hot coffee without sugar");
    }

    @Test
    public void should_report() {
        thereAreNeverShortage();
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, emailNotifier, beverageQuantityChecker)
                .insert(40).order(new Order(TEA))
                .insert(60).order(new Order(COFFEE))
                .insert(40).order(new Order(COFFEE))
                .insert(100).order(new Order(COFFEE));

        String report = coffeeMachine.report();

        assertThat(report).isEqualTo("" +
                "chocolate 0\n" +
                "coffee 2\n" +
                "orange juice 0\n" +
                "tea 1\n" +
                "total 160"
        );
    }

    @Test
    public void should_notify_if_there_is_a_shortage() {
        thereAreNeverShortage();
        given(beverageQuantityChecker.isEmpty(TEA)).willReturn(TRUE);
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, emailNotifier, beverageQuantityChecker);

        coffeeMachine.insert(40).order(new Order(TEA));

        verify(drinkMaker).command("M:No more tea");
        verify(emailNotifier).notifyMissingDrink(TEA);
    }
}