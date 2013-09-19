package parsing;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.fest.assertions.Assertions.assertThat;

public class CsvToSQLTest {

    @Test
    public void should_transform_csv_to_sql() {
        assertThat(new CsvToSQL(new ByteArrayInputStream("1/20/2006,CAC 40,4773.48,ACCOR SA-CAC 40,32.849".getBytes())).toString())
                .isEqualTo("insert into price (date, value, security_ticker) values ('2006-01-20', 4773.48, 'CAC 40'), ('2006-01-20', 32.849, 'ACCOR SA-CAC 40');");
    }

    @Test
    public void should_transform_two_lines_of_csv_to_sql() {
        assertThat(new CsvToSQL(new ByteArrayInputStream("1/20/2006,CAC 40,4773.48,ACCOR SA-CAC 40,32.849\n1/27/2006,CAC 40,4956.6,ACCOR SA-CAC 40,32.2122".getBytes())).toString())
                .isEqualTo("" +
                        "insert into price (date, value, security_ticker) values ('2006-01-20', 4773.48, 'CAC 40'), ('2006-01-20', 32.849, 'ACCOR SA-CAC 40');\n" +
                        "insert into price (date, value, security_ticker) values ('2006-01-27', 4956.6, 'CAC 40'), ('2006-01-27', 32.2122, 'ACCOR SA-CAC 40');");
    }

}
