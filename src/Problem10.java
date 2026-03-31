import java.util.*;

public class Problem10 {
    public static void main(String[] args) {
        MultiLevelCache cache = new MultiLevelCache();
        cache.addToL3("video_123", "VideoData123");
        System.out.println(cache.getVideo("video_123"));
        System.out.println(cache.getVideo("video_123")); // should hit L1
    }
}

class MultiLevelCache {
    private LinkedHashMap<String, String> L1 = new LinkedHashMap<>(16, 0.75f, true);
    private Map<String, String> L2 = new HashMap<>();
    private Map<String, String> L3 = new HashMap<>();

    public void addToL3(String videoId, String data) {
        L3.put(videoId, data);
    }

    public String getVideo(String videoId) {
        if (L1.containsKey(videoId)) return "L1 HIT → " + L1.get(videoId);
        if (L2.containsKey(videoId)) {
            L1.put(videoId, L2.get(videoId));
            return "L2 HIT → Promoted to L1";
        }
        if (L3.containsKey(videoId)) {
            L2.put(videoId, L3.get(videoId));
            return "L3 HIT → Added to L2";
        }
        return "MISS → Not found";
    }
}
