package batch;

import java.io.File;
import java.io.FileFilter;

public class RemoveAllIml {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage : java batch.RemoveAllIml <directory>");
            System.exit(1);
        }
        File directory = new File(args[0]);
        if (!directory.isDirectory()) {
            System.err.println(args[0] + " is not a directory");
            System.exit(1);
        }
        for (File subDirectory : directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        })) {
            for (File iml : subDirectory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile() && pathname.getName().endsWith(".iml");
                }
            })) {
                System.out.println("rm " + iml.getName());
                iml.delete();
            }
        }
    }
}
