package ideaproject;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ideaproject.ProjectCleaner.Status.FAILURE;
import static ideaproject.ProjectCleaner.Status.SUCCESS;
import static java.io.File.separatorChar;

public class ProjectCleaner {

    enum Status {
        SUCCESS(0),
        FAILURE(1, () -> System.err.println("java " + ProjectCleaner.class.getCanonicalName() + " <idea project directory to clean>"));

        private final Runnable task;
        private final int status;

        Status(int status) {
            this.status = status;
            this.task = null;
        }

        Status(int status, Runnable task) {
            this.status = status;
            this.task = task;
        }

        Status runEndTask() {
            Optional.ofNullable(task).ifPresent(Runnable::run);
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

    private static final List<String> directoriesToDelete = List.of(".idea", "target", "node_modules");

    private static void cleanDirectory(File file) {
        for (File currentFile : Objects.requireNonNull(file.listFiles(ProjectCleaner::isDirectoryButNotCodeSamples))) {
            if (directoriesToDelete.contains(currentFile.getName())) {
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

    private static void deleteDirectory(File directory) {
        Arrays.stream(Objects.requireNonNull(directory.listFiles())).forEach(currentFile -> {
            if (currentFile.isDirectory()) {
                deleteDirectory(currentFile);
            } else {
                currentFile.delete();
            }
        });
        directory.delete();
    }

}
