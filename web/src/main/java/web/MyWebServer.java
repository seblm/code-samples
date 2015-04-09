package web;

import net.codestory.http.WebServer;
import net.codestory.http.payload.Payload;

public class MyWebServer {
    private final WebServer server;

    public MyWebServer() {
        this.server = new WebServer().configure(routes -> routes.get("/", new Payload("text/plain", "error message", 412)));
    }

    void start() {
        server.start();
    }
    
    void stop() {
        server.stop();
    }
    
    public static void main(String[] args) {
        new MyWebServer().start();
    }
}
