import static java.text.DateFormat.MEDIUM;
import static java.util.Calendar.*;
import static java.util.Locale.US;

void main() {
    Calendar date = GregorianCalendar.getInstance();
    date.set(DAY_OF_MONTH, 13);
    date.set(MONTH, 8);
    date.set(YEAR, 2012);

    IO.println(DateFormat.getDateInstance(MEDIUM, US).format(date.getTime()));
}
