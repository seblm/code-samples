package ideaproject;

import org.junit.jupiter.api.Test;

import static ideaproject.ProjectCleaner.Status;
import static ideaproject.ProjectCleaner.Status.FAILURE;
import static ideaproject.ProjectCleaner.launchCleanDirectory;
import static org.assertj.core.api.Assertions.assertThat;

public class ProjectCleanerTest {

    @Test
    public void should_fail_if_no_path_provided() {
        Status status = launchCleanDirectory();

        assertThat(status).isEqualTo(FAILURE);
    }

    @Test
    public void should_fail_if_more_than_one_argument_given() {
        Status status = launchCleanDirectory("first argument", "second argument");

        assertThat(status).isEqualTo(FAILURE);
    }

}
