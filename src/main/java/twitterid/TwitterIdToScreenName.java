package twitterid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterIdToScreenName {

  public static final Pattern TWITTER_URL_PATTERN = Pattern.compile("^https://twitter.com/(.*)$");

  public static String getScreenNameFromId(Integer id) throws IOException {
    HttpURLConnection urlConnection = null;
    try {
      urlConnection = (HttpURLConnection) new URL("https://twitter.com/account/redirect_by_id?id=" + id).openConnection();
      urlConnection.getInputStream();
      return extractScreeNameFromTwitterURL(urlConnection.getURL());
    } finally {
      if (urlConnection != null) {
        urlConnection.disconnect();
      }
    }
  }

  private static String extractScreeNameFromTwitterURL(final URL twitterURL) {
    Matcher twitterURLMatcher = TWITTER_URL_PATTERN.matcher(twitterURL.toString());
    if (twitterURLMatcher.matches()) {
      return twitterURLMatcher.group(1);
    }
    return null;
  }

  public static void main(String... args) {
    final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    try {
      String currentLine;
      while ((currentLine = input.readLine()) != null) {
        try {
          Integer twitterId = Integer.valueOf(currentLine);
          System.out.print(twitterId);
          System.out.print("\thttps://twitter.com/");
          System.out.println(getScreenNameFromId(twitterId));
        } catch (NumberFormatException e) {
          System.err.println(currentLine);
        }
      }
    } catch (IOException e) {
      e.printStackTrace(System.err);
    }
  }

}
