package ideaproject;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import static ideaproject.ProjectCleaner.Status.FAILURE;
import static ideaproject.ProjectCleaner.Status.SUCCESS;
import static java.io.File.separatorChar;

public class ProjectCleaner {

    enum Status {
        SUCCESS(0),
        FAILURE(1, () -> System.err.println("java " + ProjectCleaner.class.getCanonicalName() + " <idea project directory to clean>"));

        private final Optional<Runnable> task;
        private final int status;

        Status(int status) {
            this.status = status;
            this.task = Optional.empty();
        }

        Status(int status, Runnable task) {
            this.status = status;
            this.task = Optional.of(task);
        }

        Status runEndTask() {
            task.ifPresent(Runnable::run);
            return this;
        }

        int status() {
            return status;
        }
    }

    public static void main(String... args) {
        System.exit(launchCleanDirectory(args).runEndTask().status());
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
        for (File currentFile : file.listFiles(pathname -> isDirectoryButNotCodeSamples(pathname) || isDotIML(pathname))) {
            if (currentFile.getName().equals(".idea") || currentFile.getName().equals("target") || currentFile.getName().equals("node_modules")) {
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

    private static boolean isDirectoryButNotCodeSamples(File pathname) {
        return pathname.isDirectory() && !pathname.getName().equals("code-samples");
    }

    private static boolean isDotIML(File pathname) {
        return pathname.isFile() && pathname.getName().endsWith(".iml");
    }

    private static void deleteDirectory(File directory) {
        Arrays.stream(directory.listFiles($ -> true)).forEach(currentFile -> {
            if (currentFile.isDirectory()) {
                deleteDirectory(currentFile);
            } else {
                currentFile.delete();
            }
        });
        directory.delete();
    }

}
