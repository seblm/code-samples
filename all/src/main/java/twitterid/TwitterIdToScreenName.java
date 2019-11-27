package twitterid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Optional.empty;

public class TwitterIdToScreenName {

  public static final Pattern TWITTER_URL_PATTERN = Pattern.compile("^.*<span class=\"nickname\">@(.+)</span>.*$");

  static Optional<String> getScreenNameFromId(Integer id) {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(twitterURL(id).openStream()))) {
      return extractScreenNameFromIntentPage(in);
    } catch (IOException e) {
      return empty();
    }
  }

  private static URL twitterURL(int id) {
    try {
      return new URL(format("https://twitter.com/intent/user?user_id=15370457", id));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private static Optional<String> extractScreenNameFromIntentPage(BufferedReader intentPage) {
    return intentPage.lines()
            .map(TWITTER_URL_PATTERN::matcher)
            .filter(Matcher::matches)
            .map(matcher -> matcher.group(1))
            .findFirst();
  }

  public static void main(String... args) {
    final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    try {
      String currentLine;
      while ((currentLine = input.readLine()) != null) {
        try {
          Integer twitterId = Integer.valueOf(currentLine);
          System.out.print(twitterId + "\thttps://twitter.com/" + getScreenNameFromId(twitterId));
        } catch (NumberFormatException e) {
          System.err.println(currentLine);
        }
      }
    } catch (IOException e) {
      e.printStackTrace(System.err);
    }
  }

}
