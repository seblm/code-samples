package date;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.text.DateFormat.MEDIUM;
import static java.text.DateFormat.getDateInstance;
import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.MILLISECOND;
import static java.util.Locale.US;
import static java.util.TimeZone.getTimeZone;
import static org.assertj.core.api.Assertions.assertThat;

public class MyBirthDayTest {

    @Test
    public void my_birthday_is_clean() {
        Calendar myBirthday = GregorianCalendar.getInstance(getTimeZone("UTC"));
        myBirthday.set(1981, DECEMBER, 24, 0, 0, 0);
        myBirthday.set(MILLISECOND, 0);

        assertThat(myBirthday.getTimeInMillis()).isEqualTo(378000000000l);
    }

    @Test
    public void next_round_birthday() {
        Calendar nextRoundBirthday = GregorianCalendar.getInstance(getTimeZone("UTC"));
        nextRoundBirthday.setTimeInMillis(379000000000l);

        assertThat(getDateInstance(MEDIUM, US).format(nextRoundBirthday.getTime())).isEqualTo("Jan 4, 1982");
    }

    @Test
    public void next_next_round_birthday() {
        Calendar nextRoundBirthday = GregorianCalendar.getInstance(getTimeZone("UTC"));
        nextRoundBirthday.setTimeInMillis(380000000000l);

        assertThat(getDateInstance(MEDIUM, US).format(nextRoundBirthday.getTime())).isEqualTo("Jan 16, 1982");
    }

}
