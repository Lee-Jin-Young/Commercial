package org.hanghea99.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final RedisTemplate<String, Object> redisTemplate;

    // 재고 초기화
    public void initializeInventory(int productId, int initialStock) {
        redisTemplate.opsForValue().set("inventory:" + productId, initialStock);
    }

    // 재고 수량 가져오기
    public int getInventory(int productId) {
        return (Integer) redisTemplate.opsForValue().get("inventory:" + productId);
    }

    // 결제 화면 진입 시 재고 감소 및 결제 진행 중 수량 증가
    public boolean enterPaymentPage(int productId) {
        int currentInventory = getInventory(productId);
        if (currentInventory > 0) {
            redisTemplate.opsForValue().increment("in_progress:" + productId);
            redisTemplate.opsForValue().decrement("inventory:" + productId);
            return true;
        }
        return false;
    }

    // 결제 완료 시
    public void completePayment(int productId, int orderId) {
        redisTemplate.opsForValue().decrement("in_progress:" + productId);
        // MySQL에서 주문 상태 업데이트 로직
        updateOrderStatus(orderId, "COMPLETED");
    }

    // 결제 실패 시 (고객 이탈)
    public void failPayment(int productId, int orderId) {
        redisTemplate.opsForValue().increment("inventory:" + productId);
        redisTemplate.opsForValue().decrement("in_progress:" + productId);
        // MySQL에서 주문 상태 업데이트 로직
        updateOrderStatus(orderId, "FAILED");
    }

    // 주문 상태 업데이트 (MySQL)
    private void updateOrderStatus(int orderId, String status) {
        // MySQL 업데이트 로직 구현
    }

    // 오픈 시간 체크
    public boolean isPurchaseEnabled(int productId) {
        LocalTime openTime = LocalTime.parse((String) redisTemplate.opsForValue().get("open_time"));
        return LocalTime.now().isAfter(openTime);
    }

    // 결제 시도 후 고객 이탈 시뮬레이션
    public void simulateAbandonment(int productId) {
        double abandonmentRate = 0.2;
        int inProgress = (Integer) redisTemplate.opsForValue().get("in_progress:" + productId);
        int abandoned = (int) (inProgress * abandonmentRate);
        for (int i = 0; i < abandoned; i++) {
            failPayment(productId, 1);
        }
    }
}
