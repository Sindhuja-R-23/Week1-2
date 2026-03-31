import java.util.*;

public class Problem9 {
    public static void main(String[] args) {
        List<Transaction> txns = Arrays.asList(
                new Transaction(1, 500, "Store A", "10:00"),
                new Transaction(2, 300, "Store B", "10:15"),
                new Transaction(3, 200, "Store C", "10:30")
        );

        FraudDetector detector = new FraudDetector();
        List<int[]> result = detector.findTwoSum(txns, 500);
        for (int[] pair : result) {
            System.out.println("Two-Sum Pair: " + pair[0] + ", " + pair[1]);
        }
    }
}

class Transaction {
    int id, amount;
    String merchant, time;

    Transaction(int id, int amount, String merchant, String time) {
        this.id = id; this.amount = amount; this.merchant = merchant; this.time = time;
    }
}

class FraudDetector {
    public List<int[]> findTwoSum(List<Transaction> txns, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();
        for (Transaction t : txns) {
            if (map.containsKey(target - t.amount)) {
                result.add(new int[]{map.get(target - t.amount), t.id});
            }
            map.put(t.amount, t.id);
        }
        return result;
    }
}
