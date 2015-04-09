package graph;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    private final Map<City, Map<City, Double>> cities;

    private Graph(Map<City, Map<City, Double>> cities) {
        this.cities = cities;
    }

    public static GraphBuilder connect(City startCity, Double toll, City endCity) {
        return new GraphBuilder(startCity, toll, endCity);
    }

    public Map<City, Double> get(City startCity) {
        return cities.get(startCity);
    }

    public static class GraphBuilder {

        private final Map<City, Map<City, Double>> cities;

        public GraphBuilder(City startCity, Double toll, City endCity) {
            cities = new HashMap<City, Map<City, Double>>();
            connect(startCity, toll, endCity);
        }

        public GraphBuilder connect(City startCity, Double toll, City endCity) {
            Map<City, Double> startCityMap = new HashMap<City, Double>();
            startCityMap.put(endCity, toll);
            cities.put(startCity, startCityMap);
            Map<City, Double> endCityMap = new HashMap<City, Double>();
            endCityMap.put(startCity, toll);
            cities.put(endCity, endCityMap);
            return this;
        }

        public Graph build() {
            return new Graph(cities);
        }

    }
}
