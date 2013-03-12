package graph;

public class CheaperJourneyResolver {

    private Graph cities;

    public CheaperJourneyResolver(Graph cities) {
        this.cities = cities;
    }

    public Double resolve(City startCity, City endCity) {
        return cities.get(startCity).get(endCity);
    }

}
