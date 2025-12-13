package secretsanta;

record Person(String firstName, String lastName, String email) {

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
