package graph;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class CheaperJourneyResolverTest {

    @Test
    public void should_get_simple_path_between_two_cities() {
        City aCity = new City('A');
        City bCity = new City('B');
        Graph cities = Graph.connect(aCity, 14.3, bCity).build();
        CheaperJourneyResolver cheaperJourneyResolver = new CheaperJourneyResolver(cities);

        assertThat(cheaperJourneyResolver.resolve(aCity, bCity)).isEqualTo(14.3);
    }

    // @Test
    public void should_get_cheaper_path_between_a_city_and_two_others() {
        City fromCity = new City('A');
        City firstCity = new City('B');
        City secondCity = new City('C');
        Graph cities = Graph
                .connect(fromCity, 18.9, firstCity)
                .connect(fromCity, 9.4, secondCity)
                .build();
        CheaperJourneyResolver cheaperJourneyResolver = new CheaperJourneyResolver(cities);

        assertThat(cheaperJourneyResolver.resolve(fromCity, secondCity)).isEqualTo(9.4);
    }

    // @Test
    public void should_get_cheaper_path_between_three_cities() {
        City startCity = new City('A');
        City middleCity = new City('B');
        City endCity = new City('C');
        Graph cities = Graph
                .connect(startCity, 18.9, middleCity)
                .connect(middleCity, 9.4, endCity)
                .build();
        CheaperJourneyResolver cheaperJourneyResolver = new CheaperJourneyResolver(cities);

        assertThat(cheaperJourneyResolver.resolve(startCity, endCity)).isEqualTo(18.9 + 9.4);
    }

}
