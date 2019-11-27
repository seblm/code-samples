package encoding;

import org.junit.Test;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class XmlParsing {
    @Test
    public void should_reproduce_strange_parsing_error_with_given_xml_file() {
        try (InputStream file = getClass().getResourceAsStream("/TEST-com.vidal.excalibur.service.resource.RestTestSuite.xml")) {
            try {
                SAXParserFactory.newInstance().newSAXParser().parse(file, new DefaultHandler());
            } catch (Throwable thrown) {
                assertThat(thrown.getClass().getName())
                        .as("name of the class instead of using isInstanceOf and an import because this import generate a warning during compilation")
                        .isEqualTo("org.xml.sax.SAXParseException");
                assertThat(thrown).hasMessage("Octet 1 de la sxE9quence UTF-8 xE0 1 octets non valide.");
            }
        } catch (IOException e) {
            fail("", e);
        }
    }
}
