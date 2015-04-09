package jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class ParseWithJackson {
    public static void main(String[] args) throws IOException {
        List<Tick> ticks = new JsonFactory(new ObjectMapper())
                .createParser(new File("~/src/code-elevator-dgageot/src/main/resources/users.json"))
                .readValueAs(new TypeReference<List<Tick>>() { });

        System.out.println("lowerFloor = " + ticks.stream()
                .map(tick -> Stream.of(tick.getUsers()).map(user -> Math.min(user[0], user[1])).reduce(Math::min).get())
                .reduce(Math::min));
        System.out.println("higherFloor = " + ticks.stream()
                .map(tick -> Stream.of(tick.getUsers()).map(user -> Math.max(user[0], user[1])).reduce(Math::max).get())
                .reduce(Math::max));
    }
}
