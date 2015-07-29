import java.io.*;

import static java.util.Arrays.stream;

public class UbiquitousLanguageJavaParser {
    private final File directory;

    public UbiquitousLanguageJavaParser(File directory) {
        this.directory = directory;
    }

    public String produceReport() {
        StringBuilder out = new StringBuilder();
        File[] allFiles = directory.listFiles();
        if (allFiles == null) {
            throw new RuntimeException();
        }
        stream(allFiles).forEach(file -> {
            try (BufferedReader javaFile = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                javaFile.lines().forEach(lines -> stream(lines.split("\\s+"))
                        .map(String::trim)
                        .filter(String::isEmpty)
                        .forEach(word -> out.append('-').append(word).append('-')));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
        return out.toString();
    }
}
