package org.hanghea99.stockservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Stock getStockFromRedis(Long productId) {
        return stockRepository.findById(productId);
    }
}
