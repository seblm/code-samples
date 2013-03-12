package encoding;

import org.junit.Test;

import java.nio.charset.Charset;

public class EncodingTest {

    @Test
    public void shouldDumpSupportedEncoding() {
        System.out.println("name\tdisplayName\tisRegistered\tcanEncode\taliases");
        for (Charset charset : Charset.availableCharsets().values()) {
            System.out.println(charset.name() + "\t" + charset.displayName() + "\t" + charset.isRegistered() + "\t" +
                    charset.canEncode() + "\t" + dumpAliases(charset));
        }
    }

    private String dumpAliases(Charset charset) {
        StringBuilder dump = new StringBuilder();
        boolean firstPass = true;
        for (String alias : charset.aliases()) {
            if (firstPass) {
                firstPass = false;
            } else {
                dump.append(", ");
            }
            dump.append(alias);
        }
        return dump.toString();
    }
}
