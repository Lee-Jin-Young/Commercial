package org.hanghea99.order.utilAndSecurity;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.domain.KeyOrderStatus;
import org.hanghea99.order.domain.Order;
import org.hanghea99.order.domain.OrderStatus;
import org.hanghea99.order.repository.KeyOrderStatusRepository;
import org.hanghea99.order.repository.OrderRepository;
import org.hanghea99.order.repository.OrderStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DummyDataLoader implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final KeyOrderStatusRepository keyOrderStatusRepository;

    @Override
    public void run(String... args) {
        // Create KeyOrderType
        KeyOrderStatus preparingOrderStatus = KeyOrderStatus.builder().status("상품 준비 중").build();
        KeyOrderStatus shippingOrderStatus = KeyOrderStatus.builder().status("배송 중").build();
        KeyOrderStatus arrivedOrderStatus = KeyOrderStatus.builder().status("배송 완료").build();
        KeyOrderStatus cancelOrderStatus = KeyOrderStatus.builder().status("주문 취소").build();
        KeyOrderStatus onReturnStatus = KeyOrderStatus.builder().status("반품 진행 중").build();
        KeyOrderStatus returnedStatus = KeyOrderStatus.builder().status("반품 완료").build();
        keyOrderStatusRepository.saveAll(Arrays.asList(
                preparingOrderStatus, shippingOrderStatus, arrivedOrderStatus,
                cancelOrderStatus, onReturnStatus, returnedStatus
        ));


        // Create dummy orders with corresponding order statuses
        LocalDateTime now = LocalDateTime.now();
        Order order1 = createOrderWithStatus(now.minusDays(5), true, preparingOrderStatus);
        Order order2 = createOrderWithStatus(now.minusDays(3), true, preparingOrderStatus);
        Order order3 = createOrderWithStatus(now.minusDays(1), true, shippingOrderStatus);
        Order order4 = createOrderWithStatus(now.minusDays(2), true, arrivedOrderStatus);
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
    }

    private Order createOrderWithStatus(LocalDateTime orderDate, boolean isNow, KeyOrderStatus keyOrderStatus) {
        Order order = Order.builder()
                .orderDate(orderDate)
                .quantity(2L)
                .userId(1L)
                .productId(1L)
                .build();

        // Save the order first to generate the order ID
        order = orderRepository.save(order);

        OrderStatus orderStatus = OrderStatus.builder()
                .regdate(orderDate)
                .isNow(isNow)
                .orderStatusKeyId(keyOrderStatus.getKeyOrderStatusId())
                .orderId(order.getOrderId()) // Assign the generated order ID
                .build();

        orderStatusRepository.save(orderStatus);

        return order;
    }
}

