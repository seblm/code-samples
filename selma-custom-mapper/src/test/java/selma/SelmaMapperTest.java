package selma;

import fr.xebia.extras.selma.Selma;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import selma.model.Dest;
import selma.model.From;
import selma.model.FromCustom;
import selma.model.FromInternal;

public class SelmaMapperTest {
    
    @Test
    public void should_use_lazy_loaded_selma_mapper_from_a_custom_selma_mapper() {
        selma.lazy.SelmaMapper mapper = Selma.builder(selma.lazy.SelmaMapper.class).build();

        Dest dest = mapper.toDest(from("value"));

        assertThat(dest).hasValue("value customized");
    }

    @Test
    public void should_use_eagerly_loaded_selma_mapper_from_a_custom_selma_mapper() {
        selma.eager.SelmaMapper mapper = Selma.builder(selma.eager.SelmaMapper.class).build();

        Dest dest = mapper.toDest(from("value"));

        assertThat(dest).hasValue("value customized");
    }

    private static From from(String value) {
        return new From(new FromCustom(new FromInternal(value)));
    }

    private static DestAssertion assertThat(Dest actual) {
        return new DestAssertion(actual);
    }

    private static class DestAssertion extends AbstractAssert<DestAssertion, Dest> {
        private DestAssertion(Dest actual) {
            super(actual, DestAssertion.class);
        }

        private DestAssertion hasValue(String expectedValue) {
            Assertions.assertThat(actual.getCustom().getInternal().getValue()).isEqualTo(expectedValue);
            return this;
        }
    }
}
