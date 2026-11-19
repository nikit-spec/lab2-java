import java.util.LinkedHashMap;
import java.util.Map;

public final class City {
    private final String name;
    private final Map<City, Integer> routes = new LinkedHashMap<>();

    public City(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Укажите название города.");
        }
        this.name = name.strip();
    }

    public String getName() {
        return name;
    }

    public void addRoute(City destination, int cost) {
        if (destination == null || cost < 0) {
            throw new IllegalArgumentException("Укажите город назначения и неотрицательную стоимость.");
        }
        routes.put(destination, cost);
    }

    @Override
    public String toString() {
        String result = name + " → [";
        String separator = "";
        for (City city : routes.keySet()) {
            result += separator + city.name + ":" + routes.get(city);
            separator = ", ";
        }
        return result + "]";
    }
}
