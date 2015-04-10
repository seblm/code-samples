package selma.eager;

import fr.xebia.extras.selma.Mapper;
import selma.model.Dest;
import selma.model.DestInternal;
import selma.model.From;
import selma.model.FromInternal;

@Mapper(withCustom = CustomSelmaMapper.class)
public interface SelmaMapper {
    Dest toDest(From from);
    DestInternal toDestInternal(FromInternal internal);
}
