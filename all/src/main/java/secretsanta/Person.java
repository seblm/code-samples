package secretsanta;

class Person {

    final String firstName;
    final String lastName;
    final String email;

    Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    boolean canHaveSantaAs(Person santa) {
        return !lastName.equals(santa.lastName);
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
