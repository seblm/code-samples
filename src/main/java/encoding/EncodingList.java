package encoding;

import java.nio.charset.Charset;
import java.util.Map;

import static java.nio.charset.Charset.availableCharsets;

public class EncodingList {

   public static void main(String[] args) {
      System.out.println("Charset.forName\tcharset.name\tcharset.displayName\taliases");
      for (Map.Entry<String, Charset> charset : availableCharsets().entrySet()) {
         StringBuilder currentCharset = new StringBuilder();
         currentCharset.append(charset.getKey()).append('\t');
         currentCharset.append(charset.getValue().name()).append('\t');
         currentCharset.append(charset.getValue().displayName());

         for (String alias : charset.getValue().aliases()) {
            currentCharset.append('\t').append(alias);
         }

         System.out.println(currentCharset.toString());
      }
   }

}
