package secretsantas;

public class Person {
    private final String firstName;
    final String lastName;
    private final String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public boolean canHaveSantasAs(Person santas) {
        return !lastName.equals(santas.lastName);
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName + " <" + email + '>';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return email.equals(person.email);

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
