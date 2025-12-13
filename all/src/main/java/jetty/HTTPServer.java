package jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.Callback;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.eclipse.jetty.http.HttpHeader.CONTENT_TYPE;

public class HTTPServer {

    static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new Handler.Abstract() {
            @Override
            public boolean handle(Request request, Response response, Callback callback) throws IOException, ServletException {
                response.getHeaders().put(CONTENT_TYPE, "text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.write(true, ByteBuffer.wrap("hello world".getBytes()), callback);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        server.start();
        server.join();
    }

}
