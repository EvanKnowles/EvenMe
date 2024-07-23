package za.co.knonchalant.evenme.cache;

public class CacheRecord {
    private final String result;
    private final boolean cacheHit;

    public CacheRecord(String result, boolean cacheHit) {
        this.result = result;
        this.cacheHit = cacheHit;
    }

    public String getResult() {
        return result;
    }

    public boolean wasCacheHit() {
        return cacheHit;
    }
}
