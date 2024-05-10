package org.hanghea99.order.utilAndSecurity;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.domain.KeyOrderStatus;
import org.hanghea99.order.domain.Order;
import org.hanghea99.order.repository.KeyOrderStatusRepository;
import org.hanghea99.order.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DummyDataLoader implements CommandLineRunner {

    private final KeyOrderStatusRepository keyOrderStatusRepository;
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) {
        // Create KeyOrderType
        KeyOrderStatus shippingOrderStatus = KeyOrderStatus.builder().orderStatus("배송 중").build();
        KeyOrderStatus arrivedOrderStatus = KeyOrderStatus.builder().orderStatus("배송 완료").build();
        KeyOrderStatus cancelOrderStatus = KeyOrderStatus.builder().orderStatus("주문 취소").build();
        KeyOrderStatus onReturnStatus = KeyOrderStatus.builder().orderStatus("반품 진행 중").build();
        KeyOrderStatus returnedStatus = KeyOrderStatus.builder().orderStatus("반품 완료").build();
        keyOrderStatusRepository.save(shippingOrderStatus);
        keyOrderStatusRepository.save(arrivedOrderStatus);
        keyOrderStatusRepository.save(cancelOrderStatus);
        keyOrderStatusRepository.save(onReturnStatus);
        keyOrderStatusRepository.save(returnedStatus);


        // Create dummy orders
        LocalDateTime now = LocalDateTime.now();
        Order order1 = Order.builder()
                .orderDate(now.minusDays(5))
                .quantity(2L)
                .userId(1L)
                .productId(1L)
                .build();
        Order order2 = Order.builder()
                .orderDate(now.minusDays(3))
                .quantity(1L)
                .userId(1L)
                .productId(2L)
                .build();
        Order order3 = Order.builder()
                .orderDate(now.minusDays(1))
                .quantity(3L)
                .userId(2L)
                .productId(1L)
                .build();
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
    }
}

