package security;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class PasswordGeneratorTest {

    @Test
    public void should_generate_random_password() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        String generatedPassword = passwordGenerator.generate(16);

        assertThat(generatedPassword).hasSize(16).matches("[a-zA-Z0-9_-]+");
    }

}
