package encoding;

import java.nio.charset.Charset;
import java.util.Map;

import static java.nio.charset.Charset.availableCharsets;

public class Encoding {

    public static void main(String[] args) {
        StringBuilder currentCharset = new StringBuilder();
        for (Map.Entry<String, Charset> charset : availableCharsets().entrySet()) {
            currentCharset.append(charset.getKey()).append('\t');
            currentCharset.append(charset.getValue().name()).append('\t');
            currentCharset.append(charset.getValue().displayName());

            for (String alias : charset.getValue().aliases()) {
                currentCharset.append('\t').append(alias);
            }

            System.out.println(currentCharset);
            currentCharset.delete(0, currentCharset.length() - 1);
        }
    }

}
