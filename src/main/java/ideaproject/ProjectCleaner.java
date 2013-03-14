package ideaproject;

import java.io.File;

import static java.io.File.separatorChar;

public class ProjectCleaner {

    public static void main(String... args) {
        if (args.length != 1) {
            System.err.println("java " + ProjectCleaner.class.getCanonicalName() + " <idea project directory to clean>");
            System.exit(1);
        }

        cleanDirectory(new File(args[0]));
    }

    private static void cleanDirectory(File file) {
        for (File currentFile : file.listFiles((File pathname) ->
                pathname.isDirectory() || pathname.isFile() && pathname.getName().endsWith(".iml"))) {
            if (currentFile.getName().equals(".idea") || currentFile.getName().equals("target")) {
                System.out.println("rm -fr " + currentFile.getPath() + separatorChar);
                deleteDirectory(currentFile);
            } else if (currentFile.isDirectory()) {
                cleanDirectory(currentFile);
            } else if (currentFile.getName().endsWith(".iml")) {
                System.out.println("rm " + currentFile.getPath());
                currentFile.delete();
            }
        }
    }

    private static void deleteDirectory(File directory) {
        for (File currentFile : directory.listFiles()) {
            if (currentFile.isDirectory()) {
                deleteDirectory(currentFile);
            } else {
                currentFile.delete();
            }
        }
        directory.delete();
    }

}
