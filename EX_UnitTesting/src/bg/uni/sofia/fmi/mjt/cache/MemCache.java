package bg.uni.sofia.fmi.mjt.cache;

import bg.uni.sofia.fmi.mjt.cache.exceptions.CapacityExceededException;
import java.time.LocalDateTime;

public class MemCache<K, V> implements Cache {

    /**
     * Constructs a new Cache with the specified maximum capacity
     */
    public MemCache(long capacity){

    }

    /**
     * Constructs a new Cache with maximum capacity of 10000 items
     */
    public MemCache(){

    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public void set(Object key, Object value, LocalDateTime expiresAt) throws CapacityExceededException {

    }

    @Override
    public LocalDateTime getExpiration(Object key) {
        return null;
    }

    @Override
    public boolean remove(Object key) {
        return false;
    }

    @Override
    public long size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public double getHitRate() {
        return 0;
    }
}
