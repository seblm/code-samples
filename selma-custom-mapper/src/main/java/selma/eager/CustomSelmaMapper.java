package selma.eager;

import fr.xebia.extras.selma.Selma;
import selma.model.DestCustom;
import selma.model.DestInternal;
import selma.model.FromCustom;

public class CustomSelmaMapper {
    private SelmaMapper mapper = Selma.builder(SelmaMapper.class).build();

    public DestCustom toDestCustom(FromCustom custom) {
        DestInternal internal = mapper.toDestInternal(custom.internal);

        internal.setValue(internal.getValue() + " customized");

        return new DestCustom(internal);
    }
}
