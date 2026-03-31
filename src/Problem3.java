import java.util.*;

public class Problem3 {
    public static void main(String[] args) {
        DNSCache cache = new DNSCache();
        System.out.println(cache.resolve("google.com"));
        System.out.println(cache.resolve("google.com"));
    }
}

class DNSEntry {
    String domain, ip;
    long expiryTime;

    DNSEntry(String domain, String ip, int ttlSeconds) {
        this.domain = domain;
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

class DNSCache {
    private Map<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {
        DNSEntry entry = cache.get(domain);
        if (entry == null || entry.isExpired()) {
            String ip = queryUpstream(domain);
            cache.put(domain, new DNSEntry(domain, ip, 5));
            return "Cache MISS → " + ip;
        }
        return "Cache HIT → " + entry.ip;
    }

    private String queryUpstream(String domain) {
        return "172.217." + new Random().nextInt(255) + "." + new Random().nextInt(255);
    }
}
