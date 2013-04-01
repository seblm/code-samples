package ideaproject;

import org.junit.Test;

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

//    TODO mock files and directory in order to test all cases
//    <dependency>
//        <groupId>org.mockito</groupId>
//        <artifactId>mockito-core</artifactId>
//        <version>1.9.5</version>
//        <scope>test</scope>
//    </dependency>

}