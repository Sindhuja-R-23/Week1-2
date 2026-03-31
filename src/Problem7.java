import java.util.*;

public class Problem7 {
    public static void main(String[] args) {
        AutocompleteSystem system = new AutocompleteSystem();
        system.updateFrequency("java tutorial");
        system.updateFrequency("javascript");
        system.updateFrequency("java download");

        System.out.println("Suggestions for 'jav': " + system.search("jav"));
    }
}

class AutocompleteSystem {
    private Map<String, Integer> queryFrequency = new HashMap<>();

    public void updateFrequency(String query) {
        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {
        return queryFrequency.entrySet().stream()
                .filter(e -> e.getKey().startsWith(prefix))
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();
    }
}
