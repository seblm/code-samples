package selma;

import fr.xebia.extras.selma.Mapper;
import selma.model.DefensiveCopy;
import selma.model.From;

@Mapper
public interface SelmaMapper {
    DefensiveCopy toDest(From from);
}
