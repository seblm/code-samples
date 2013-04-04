package puzzler;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class PersonTest {

    @Test
    public void test() {
        Person person = new Person();

        person.update(person.age);

        assertThat(person.age).isEqualTo(20);
    }

}
