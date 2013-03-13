package parameters;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ParametersTest {

    @Test
    public void testParameters() {
        int i = 5;
        String string = "yes";
        Integer integer = i;
        Parameters.MyContainer myContainer = new Parameters.MyContainer("beforeCall");
        Parameters.updateValuesFromParameters(i, integer, string, myContainer);

        assertThat(i).isEqualTo(5);
        assertThat(integer).isEqualTo(5);
        assertThat(string).isEqualTo("yes");
        assertThat(myContainer.getValue()).isEqualTo("okay");
    }
}
