package secretsanta;

@FunctionalInterface
interface Emailer {

    void email(Person to, Person santa);

}
