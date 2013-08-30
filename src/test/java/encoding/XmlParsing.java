package encoding;

import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.rules.ExpectedException.none;

public class XmlParsing {

   @Rule
   public ExpectedException thrown = none();

   @Test
   public void should() throws ParserConfigurationException, SAXException, IOException {
      thrown.expect(MalformedByteSequenceException.class);
      thrown.expectMessage("Octet 1 de la séquence UTF-8 à 1 octets non valide.");

      InputStream file = getClass().getResourceAsStream("/TEST-com.vidal.excalibur.service.resource.RestTestSuite.xml");
      SAXParserFactory.newInstance().newSAXParser().parse(file, new DefaultHandler());
   }

}
