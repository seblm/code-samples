package web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class MyWebServerTest {

    private MyWebServer server = new MyWebServer();

    @Before
    public void start() {
        server.start();
    }

    @Test
    public void should_returns_412_status_code() throws IOException {
        URL url = new URL("http://localhost:8080");
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        assertThat(connection.getResponseCode()).isEqualTo(412);
    }

    @After
    public void stop() {
        server.stop();
    }
}
