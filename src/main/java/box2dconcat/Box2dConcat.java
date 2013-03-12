package box2dconcat;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static java.io.File.separatorChar;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Box2dConcat {

    public static String removeCopyright(String fileContent) {
        StringBuilder filteredFileContent = new StringBuilder();
        StringTokenizer eachLine = new StringTokenizer(fileContent, "\n", true);
        Boolean endOfCopyrightIsReached = false;
        while (eachLine.hasMoreTokens()) {
            String currentLine = eachLine.nextToken();
            if (!endOfCopyrightIsReached) {
                endOfCopyrightIsReached = currentLine.matches("^\\s*\\*/.*$");
                try {
                    eachLine.nextToken();
                } catch (NoSuchElementException e) {
                }
            } else {
                filteredFileContent.append(currentLine);
            }
        }
        return filteredFileContent.toString();
    }

    public static String concat(String pathAndFileName, String fileContent) {
        StringBuilder concatPathAndFileNameWithFileContent = new StringBuilder();
        concatPathAndFileNameWithFileContent.append("// ").append(pathAndFileName).append('\n').append(fileContent);
        return concatPathAndFileNameWithFileContent.toString();
    }

    public static void main(String[] args) {
        File directory = new File(args[0]);
        concat(directory, "", TRUE);
    }

    private static void concat(File directory, String parentDirectories, Boolean isRoot) {
        final StringBuilder dumpedFiles = new StringBuilder();
        for (final File file : directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().endsWith(".js");
            }
        })) {
            dumpedFiles.append(concat(
                    (isRoot ? "" : (parentDirectories.isEmpty() ? "" : parentDirectories + separatorChar) + directory.getName() + separatorChar) + file.getName(),
                    removeCopyright(dumpFile(file)))).append('\n');
        }
        System.out.print(dumpedFiles.toString());
        for (final File subDirectory : directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        })) {
            concat(subDirectory, isRoot ? "" : directory.getName(), FALSE);
        }
    }

    private static String dumpFile(File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String currentLine;
            StringBuilder dumpedFile = new StringBuilder();
            while ((currentLine = in.readLine()) != null) {
                dumpedFile.append(currentLine).append('\n');
            }
            return dumpedFile.toString();
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
