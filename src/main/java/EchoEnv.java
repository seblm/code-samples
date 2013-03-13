import java.io.*;

import static java.lang.Runtime.*;
import static java.lang.System.*;

public class EchoEnv {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(getRuntime().exec("/usr/bin/env").getInputStream()));
		String currentLine;
		while ((currentLine = in.readLine()) != null) {
			if (currentLine.startsWith("PATH")) {
				out.println(currentLine);
			}
		}
	}

}
