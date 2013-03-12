package encoding;

import java.nio.charset.Charset;

public class EncodingList {

    public static void main(String[] args) {
        for (Charset charset : Charset.availableCharsets().values()) {
            StringBuilder out = new StringBuilder();
            out.append(charset.name());
            out.append('\t').append(charset.displayName());
            for (String alias : charset.aliases()) {
                out.append('\t').append(alias);
            }
            System.out.println(out.toString());
        }
    }

}
