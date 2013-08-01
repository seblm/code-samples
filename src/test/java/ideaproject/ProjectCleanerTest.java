package ideaproject;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileFilter;

import static ideaproject.ProjectCleaner.Status;
import static ideaproject.ProjectCleaner.Status.FAILURE;
import static ideaproject.ProjectCleaner.launchCleanDirectory;
import static org.fest.assertions.Assertions.assertThat;

public class ProjectCleanerTest {

    @Test
    public void should_fail_if_no_path_provided() throws Exception {
        Status status = launchCleanDirectory();

        assertThat(status).isEqualTo(FAILURE);
    }

    @Test
    public void should_fail_if_more_than_one_argument_given() throws Exception {
        Status status = launchCleanDirectory("first argument", "second argument");

        assertThat(status).isEqualTo(FAILURE);
    }

}
