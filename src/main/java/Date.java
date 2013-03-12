import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.text.DateFormat.MEDIUM;
import static java.util.Calendar.*;
import static java.util.Locale.US;

public class Date {

    public static void main(String[] args) {
        Calendar date = GregorianCalendar.getInstance();
        date.set(DAY_OF_MONTH, 13);
        date.set(MONTH, 8);
        date.set(YEAR, 2012);

        System.out.println(DateFormat.getDateInstance(MEDIUM, US).format(date.getTime()));
    }
}
