package ideaproject;

import java.io.File;
import java.io.FileFilter;

import static ideaproject.ProjectCleaner.Status.FAILURE;
import static ideaproject.ProjectCleaner.Status.SUCCESS;
import static java.io.File.separatorChar;

public class ProjectCleaner {

    static enum Status {
        SUCCESS, FAILURE
    }

    public static void main(String... args) {
        Status status = launchCleanDirectory(args);
        if (status == FAILURE) {
            System.err.println("java " + ProjectCleaner.class.getCanonicalName() + " <idea project directory to clean>");
            System.exit(1);
        }
    }

    static Status launchCleanDirectory(String... args) {
        if (args.length != 1) {
            return FAILURE;
        }

        File root = new File(args[0]);
        if (!root.isDirectory()) {
            return FAILURE;
        }

        cleanDirectory(root);
        return SUCCESS;
    }

    private static void cleanDirectory(File file) {
        for (File currentFile : file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.isFile() && pathname.getName().endsWith(".iml");
            }
        })) {
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
