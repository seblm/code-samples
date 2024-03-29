package secretsanta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SecretSantaTest {

    @Mock
    private Emailer emailer;

    @Captor
    private ArgumentCaptor<Person> to;

    @Captor
    private ArgumentCaptor<Person> santa;

    @Test
    public void should_choose_a_santa() {
        Person luke = new Person("Luke", "Skywalker", "luke@theforce.net");
        Person leia = new Person("Leia", "Skywalker", "leia@therebellion.org");
        Person toula = new Person("Toula", "Portokalos", "toula@manhunter.org");
        Person gus = new Person("Gus", "Portokalos", "gus@weareallfruit.net");
        Person bruce = new Person("Bruce", "Wayne", "bruce@imbatman.com");
        Person virgil = new Person("Virgil", "Brigman", "virgil@rigworkersunion.org");
        Person lindsey = new Person("Lindsey", "Brigman", "lindsey@iseealiens.net");
        List<Person> persons = asList(luke, leia, toula, gus, bruce, virgil, lindsey);
        SecretSanta secretSanta = new SecretSanta(persons, emailer);

        secretSanta.chooseSantas();

        checkSantas(persons);
    }

    @Test
    public void should_choose_a_santa_2() {
        Person luke = new Person("Luke", "Skywalker", "luke@theforce.net");
        Person toula = new Person("Toula", "Portokalos", "toula@manhunter.org");
        Person virgil = new Person("Virgil", "Brigman", "virgil@rigworkersunion.org");
        List<Person> persons = asList(luke, toula, virgil);
        SecretSanta secretSanta = new SecretSanta(persons, emailer);

        secretSanta.chooseSantas();

        checkSantas(persons);
    }

    private void checkSantas(List<Person> persons) {
        verify(emailer, times(persons.size())).email(to.capture(), santa.capture());
        Map<Person, Person> santas = new HashMap<>();
        for (int i = 0; i < persons.size(); i++) {
            santas.put(to.getAllValues().get(i), santa.getAllValues().get(i));
        }
        Person[] personsAsArray = persons.toArray(new Person[0]);
        assertThat(santas.keySet()).containsOnly(personsAsArray);
        for (Map.Entry<Person, Person> personAndSantas : santas.entrySet()) {
            assertThat(personAndSantas.getKey())
                    .as("santas for %s could not be hitself", personAndSantas.getKey())
                    .isNotEqualTo(personAndSantas.getValue());
            assertThat(personAndSantas.getKey().lastName)
                    .as("santas for %s could not belongs to same family", personAndSantas.getKey())
                    .isNotEqualTo(personAndSantas.getValue().lastName);
        }
        assertThat(new ArrayList<>(santas.values())).containsOnly(personsAsArray);
    }

}
