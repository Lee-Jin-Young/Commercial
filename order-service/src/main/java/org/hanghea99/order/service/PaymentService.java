package org.hanghea99.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final InventoryService inventoryService;

    public boolean startPaymentProcess(int productId, int userId) {
        if (inventoryService.isPurchaseEnabled(productId)) {
            if (inventoryService.enterPaymentPage(productId)) {
                // 결제 화면 진입 성공 시 주문 생성 (MySQL에 주문 데이터 저장)
                createOrder(productId, userId);
                return true;
            }
        }
        return false;
    }

    public void completePaymentProcess(int productId, int orderId) {
        inventoryService.completePayment(productId, orderId);
        // 추가 비즈니스 로직 처리
    }

    public void failPaymentProcess(int productId, int orderId) {
        inventoryService.failPayment(productId, orderId);
        // 추가 비즈니스 로직 처리
    }

    private void createOrder(int productId, int userId) {
        // MySQL에 주문 데이터 저장 로직 구현
    }
}
