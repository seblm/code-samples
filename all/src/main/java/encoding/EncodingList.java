package encoding;

import java.nio.charset.Charset;
import java.util.Map;

import static java.nio.charset.Charset.availableCharsets;

public class EncodingList {

   public static void main(String[] args) {
      System.out.println("charset.name\tcharset.displayName\taliases");
      System.out.println(charsetToString(Charset.defaultCharset()));
      for (Charset charset : availableCharsets().values()) {
         System.out.println(charsetToString(charset));
      }
   }

   private static String charsetToString(Charset charset1) {
      StringBuilder currentCharset = new StringBuilder();
      currentCharset.append(charset1.name()).append('\t');
      currentCharset.append(charset1.displayName());

      for (String alias : charset1.aliases()) {
         currentCharset.append('\t').append(alias);
      }

      return currentCharset.toString();
   }

}
