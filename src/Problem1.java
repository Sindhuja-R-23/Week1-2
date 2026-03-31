import java.util.*;

public class Problem1 {
    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();
        checker.checkAvailability("john_doe"); // false if taken
        System.out.println("Suggestions: " + checker.suggestAlternatives("john_doe"));
        System.out.println("Most Attempted: " + checker.getMostAttempted());
    }
}

class UsernameChecker {
    private Map<String, Integer> users = new HashMap<>();
    private Map<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        return Arrays.asList(username + "1", username + "2", username.replace("_", "."));
    }

    public String getMostAttempted() {
        return attempts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");
    }
}
