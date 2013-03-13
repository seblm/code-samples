package properties;

import org.junit.*;

import static java.lang.System.getProperty;
import static org.fest.assertions.Assertions.assertThat;

public class SystemPropertiesTest {

	@Test
	public void shouldKnowOnWhatArchItRuns() {
		assertThat(getProperty("sun.arch.data.model")).isEqualTo("64");
		assertThat(getProperty("java.vm.name")).isEqualTo("Java HotSpot(TM) 64-Bit Server VM");
	}
}
