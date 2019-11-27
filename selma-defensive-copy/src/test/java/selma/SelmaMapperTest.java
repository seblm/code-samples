package selma;

import fr.xebia.extras.selma.Selma;
import org.junit.Ignore;
import org.junit.Test;
import selma.model.DefensiveCopy;
import selma.model.From;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class SelmaMapperTest {
    @Test
    @Ignore
    public void should_map_a_list_to_another_even_if_it_is_defensively_copied() {
        From from = new From();
        from.setValues(asList("a", "b", "c"));
        selma.SelmaMapper mapper = Selma.builder(selma.SelmaMapper.class).build();

        DefensiveCopy dest = mapper.toDest(from);

        assertThat(dest.getValues()).containsOnly("a", "b", "c");
    }
}
