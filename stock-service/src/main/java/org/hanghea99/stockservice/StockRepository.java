package org.hanghea99.stockservice;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepository {

    private static final String KEY = "Stock";

    private RedisTemplate<String, Stock> redisTemplate;
    private HashOperations<String, Long, Stock> hashOperations;

    public StockRepository(RedisTemplate<String, Stock> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(Stock stock) {
        hashOperations.put(KEY, stock.getStockId(), stock);
    }

    public Stock findById(Long stockId) {
        return hashOperations.get(KEY, stockId);
    }

    public void update(Stock stock) {
        save(stock);
    }

    public void delete(Long stockId) {
        hashOperations.delete(KEY, stockId);
    }
}

