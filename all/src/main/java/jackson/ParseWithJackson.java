package jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class ParseWithJackson {
    static void main() throws IOException {
        try (var parser = new JsonFactory(new ObjectMapper())
                .createParser(new File("~/Developer/src/code-elevator-dgageot/src/main/resources/users.json"))) {
            List<Tick> ticks = parser.readValueAs(new TypeReference<List<Tick>>() {});
            System.out.println("lowerFloor = " + ticks.stream()
                    .flatMap(tick -> Stream.of(tick.getUsers()).map(user -> Math.min(user[0], user[1])).reduce(Math::min).stream())
                    .reduce(Math::min)
                    .orElse(Integer.MAX_VALUE));
            System.out.println("higherFloor = " + ticks.stream()
                    .flatMap(tick -> Stream.of(tick.getUsers()).map(user -> Math.max(user[0], user[1])).reduce(Math::max).stream())
                    .reduce(Math::max)
                    .orElse(Integer.MIN_VALUE));
        }
    }
}
