public class Problem6 {
    public static void main(String[] args) {
        TokenBucket bucket = new TokenBucket(5, 1); // max 5 tokens, refill 1 per second

        for (int i = 0; i < 7; i++) {
            System.out.println("Request " + (i+1) + ": " + (bucket.allowRequest() ? "Allowed" : "Denied"));
        }
    }
}

class TokenBucket {
    int tokens;
    int maxTokens;
    long lastRefill;
    int refillRate; // tokens per second

    TokenBucket(int maxTokens, int refillRate) {
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.lastRefill = System.currentTimeMillis();
    }

    synchronized boolean allowRequest() {
        refill();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsed = (now - lastRefill) / 1000;
        int refillTokens = (int) (elapsed * refillRate);
        if (refillTokens > 0) {
            tokens = Math.min(maxTokens, tokens + refillTokens);
            lastRefill = now;
        }
    }
}
