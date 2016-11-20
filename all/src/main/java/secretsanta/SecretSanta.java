package secretsanta;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

class SecretSanta {

    private final List<Person> persons;
    private final Emailer emailer;
    private final Random random;

    SecretSanta(List<Person> persons, Emailer emailer) {
        this.persons = persons;
        this.emailer = emailer;
        this.random = new Random();
    }

    void chooseSantas() {
        List<Person> availableSantas = new ArrayList<>(persons);
        Collections.shuffle(availableSantas);

        Map<Person, Person> santas = new HashMap<>();
        for (int i = 0; i < persons.size(); i++) {
            santas.put(persons.get(i), availableSantas.get(i));
        }

        for (Person person : santas.keySet()) {
            Person personSanta = santas.get(person);
            if (person.canHaveSantaAs(personSanta)) {
                continue;
            }

            List<Person> candidates = persons.stream()
                    .filter(otherPerson -> {
                        Person otherPersonSantas = santas.get(otherPerson);
                        return otherPersonSantas.canHaveSantaAs(person)
                                && personSanta.canHaveSantaAs(otherPerson);
                    })
                    .collect(toList());

            if (candidates.isEmpty()) {
                throw new RuntimeException("impossible");
            }

            swapSantas(person, candidates.get(random.nextInt(candidates.size())), santas);
        }

        for (Map.Entry<Person, Person> personAndSantas : santas.entrySet()) {
            emailer.email(personAndSantas.getKey(), personAndSantas.getValue());
        }
    }

    private void swapSantas(Person person, Person otherPerson, Map<Person, Person> santas) {
        Person previousPersonSantas = santas.put(person, santas.get(otherPerson));
        santas.put(otherPerson, previousPersonSantas);
    }

    public static void main(String[] args) {
        new SecretSanta(asList(
                new Person("****", "** ***** *******", "****.*******@gmail.com"),
                new Person("******", "** ***** *******", "******.*******@gmail.com"),
                new Person("*******", "** ***** *********", "*******.**-*****@laposte.net"),
                new Person("*******", "** ***** *********", "*******_*****@hotmail.com"),
                new Person("****-******", "** *****", "*****@free.fr"),
                new Person("********", "** *****", "******@free.fr"),
                new Person("*********", "** ***** **********", "*********.*******@gmail.com"),
                new Person("********", "** ***** **********", "************@hotmail.com")
        ), new GmailEmailer("youremail@gmail.com", "16charspassword")) // please see https://security.google.com/settings/security/apppasswords
        .chooseSantas();
    }

}
