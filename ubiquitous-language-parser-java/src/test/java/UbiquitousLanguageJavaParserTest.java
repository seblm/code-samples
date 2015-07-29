import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import java.net.URISyntaxException;
import org.junit.Test;

public class UbiquitousLanguageJavaParserTest {
    @Test
    public void should_parse_one_class() {
        try {
            UbiquitousLanguageJavaParser parser = new UbiquitousLanguageJavaParser(new File(UbiquitousLanguageJavaParser.class.getResource("/one_class").toURI()));

            assertThat(parser.produceReport()).isEqualTo(
                    "" +
                            "3 SomeImportantType\n" +
                            "2 businessRule\n");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}