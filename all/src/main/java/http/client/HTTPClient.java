package http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTTPClient {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);
            try (BufferedReader content = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String currentLine;
                while ((currentLine = content.readLine()) != null) {
                    System.out.println(currentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
