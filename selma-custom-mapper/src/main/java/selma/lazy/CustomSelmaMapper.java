package selma.lazy;

import fr.xebia.extras.selma.Selma;
import selma.model.DestCustom;
import selma.model.DestInternal;
import selma.model.FromCustom;

public class CustomSelmaMapper {
    private SelmaMapper mapper;

    private SelmaMapper getMapper() {
        if (mapper == null) {
            mapper = Selma.builder(SelmaMapper.class).build();
        }
        return mapper;
    }

    public DestCustom toDestCustom(FromCustom custom) {
        DestInternal internal = getMapper().toDestInternal(custom.internal);

        internal.setValue(internal.getValue() + " customized");

        return new DestCustom(internal);
    }
}
