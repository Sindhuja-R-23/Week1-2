import java.util.*;

public class Problem4 {
    public static void main(String[] args) {
        PlagiarismDetector detector = new PlagiarismDetector();
        detector.indexDocument("essay_089", Arrays.asList("the cat sat", "on the mat"));
        Map<String, Integer> result = detector.analyzeDocument("essay_123", Arrays.asList("the cat sat", "hello world"));
        System.out.println("Matches: " + result);
    }
}

class PlagiarismDetector {
    private Map<String, Set<String>> ngramIndex = new HashMap<>();

    public void indexDocument(String docId, List<String> ngrams) {
        for (String ngram : ngrams) {
            ngramIndex.computeIfAbsent(ngram, k -> new HashSet<>()).add(docId);
        }
    }

    public Map<String, Integer> analyzeDocument(String docId, List<String> ngrams) {
        Map<String, Integer> matches = new HashMap<>();
        for (String ngram : ngrams) {
            if (ngramIndex.containsKey(ngram)) {
                for (String otherDoc : ngramIndex.get(ngram)) {
                    if (!otherDoc.equals(docId)) {
                        matches.put(otherDoc, matches.getOrDefault(otherDoc, 0) + 1);
                    }
                }
            }
        }
        return matches;
    }
}
