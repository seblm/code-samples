package http.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

import static java.lang.Integer.parseInt;
import static java.lang.Runtime.getRuntime;

public class HTTPServer {
    public static void main(String[] args) throws IOException {
        if (args.length > 2) {
            System.err.println("java http.server.HTTPServer [port] [documentRoot]");
            return;
        }
        int port = getPort(args);
        File documentRoot = getDocumentRoot(args);
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/", new FileSystemHandler(documentRoot));

        getRuntime().addShutdownHook(new Thread(() -> {
            httpServer.stop(0);
            System.out.println("HTTPServer stopped");
        }));

        httpServer.start();
        System.out.format("HTTPServer started on port %d and serving %s%n", port, documentRoot.getAbsolutePath());
    }

    private static int getPort(String[] args) {
        int port = 8080;
        for (String arg : args) {
            try {
                port = parseInt(arg);
                return port;
            } catch (NumberFormatException e) {
            }
        }
        return port;
    }

    private static File getDocumentRoot(String[] args) {
        for (String arg : args) {
            File fileFromArguments = new File(arg);
            if (fileFromArguments.isDirectory()) {
                return fileFromArguments;
            }
        }
        return new File(".").getAbsoluteFile().getParentFile();
    }

    private static class FileSystemHandler implements HttpHandler {
        private File documentRoot;

        public FileSystemHandler(File documentRoot) {
            this.documentRoot = documentRoot;
        }

        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();

            File resourceAsFile = new File(documentRoot + path);
            if (!resourceAsFile.exists()) {
                exchange.sendResponseHeaders(404, 0);
            }
            try (InputStream resource = new FileInputStream(resourceAsFile)) {

                exchange.sendResponseHeaders(200, 0);

                try (OutputStream os = exchange.getResponseBody()) {
                    final byte[] buffer = new byte[0x10000];
                    int count;
                    while ((count = resource.read(buffer)) >= 0) {
                        os.write(buffer, 0, count);
                    }
                }
            }
        }
    }
}
