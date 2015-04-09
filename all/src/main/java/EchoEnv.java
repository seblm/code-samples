import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Runtime.getRuntime;
import static java.lang.System.out;

public class EchoEnv {

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(getRuntime().exec("/usr/bin/env").getInputStream()))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                if (currentLine.startsWith("PATH")) {
                    out.println(currentLine);
                }
            }
        }
    }

}
