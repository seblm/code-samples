import java.io.*;

public class Runner {

  public static void main(String[] args) throws IOException {
    System.out.println(System.getenv("PATH"));
    Process exec = Runtime.getRuntime().exec("env");
    BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
    String currentLine;
    while ((currentLine = in.readLine()) != null) {
      System.out.println(currentLine);
    }
  }

}
