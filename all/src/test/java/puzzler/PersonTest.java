package puzzler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    @Test
    public void test() {
        Person person = new Person();

        Person.update(person.age);

        assertThat(person.age).isEqualTo(20);
    }

}
