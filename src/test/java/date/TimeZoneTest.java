package date;

import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.TimeZone.getTimeZone;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeZoneTest {

    @Test
    public void listTimeZones() {
        String threeLettersTimeZones = stream(TimeZone.getAvailableIDs())
                .filter((availableID) -> availableID.length() == 3)
                .map((availableID) -> getTimeZone(availableID))
                .sorted((timeZone1, timeZone2) -> {
                    int rawOffsetDelta = Integer.compare(timeZone1.getRawOffset(), timeZone2.getRawOffset());
                    if (rawOffsetDelta != 0) {
                        return rawOffsetDelta;
                    }
                    return timeZone1.getID().compareTo(timeZone2.getID());
                })
                .map((timeZone) -> format("%s %2dh", timeZone.getID(), timeZone.getRawOffset() / 1000 / 60 / 60))
                .reduce((availableIDs, availableID) -> availableIDs + (availableIDs.length() > 0 ? "\n" : "") + availableID)
                .get();

        assertThat(threeLettersTimeZones).isEqualTo("HST -10h\n" +
                "AST -9h\n" +
                "PST -8h\n" +
                "MST -7h\n" +
                "PNT -7h\n" +
                "CST -6h\n" +
                "EST -5h\n" +
                "IET -5h\n" +
                "PRT -4h\n" +
                "CNT -3h\n" +
                "AGT -3h\n" +
                "BET -3h\n" +
                "GMT  0h\n" +
                "UCT  0h\n" +
                "UTC  0h\n" +
                "WET  0h\n" +
                "CET  1h\n" +
                "ECT  1h\n" +
                "MET  1h\n" +
                "ART  2h\n" +
                "CAT  2h\n" +
                "EET  2h\n" +
                "EAT  3h\n" +
                "NET  4h\n" +
                "PLT  5h\n" +
                "IST  5h\n" +
                "BST  6h\n" +
                "VST  7h\n" +
                "CTT  8h\n" +
                "PRC  8h\n" +
                "JST  9h\n" +
                "ROK  9h\n" +
                "ACT  9h\n" +
                "AET 10h\n" +
                "SST 11h\n" +
                "NST 12h\n" +
                "MIT 13h");
    }

}
