package org.hanghea99.order.service;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.client.ProductClient;
import org.hanghea99.order.domain.KeyOrderStatus;
import org.hanghea99.order.domain.Order;
import org.hanghea99.order.domain.OrderStatus;
import org.hanghea99.order.dto.OrderDto;
import org.hanghea99.order.dto.ProductDto;
import org.hanghea99.order.repository.KeyOrderStatusRepository;
import org.hanghea99.order.repository.OrderRepository;
import org.hanghea99.order.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final KeyOrderStatusRepository keyOrderStatusRepository;

    private final ProductClient productClient;

    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

    // Order를 OrderDto로 변환하는 메서드
    private OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setQuantity(order.getQuantity());

        // ProductServiceFeignClient를 통해 Product 정보 호출하여 설정
        ProductDto productDto = productClient.getProductById(order.getProductId());
        orderDto.setTitle(productDto.getTitle());
        orderDto.setPrice(productDto.getPrice());

        // OrderStatus에서 isNow가 true이고 orderId와 일치하는 OrderStatus를 조회
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findByIsNowAndOrderId(true, order.getOrderId());
        if (optionalOrderStatus.isPresent()) {
            OrderStatus orderStatus = optionalOrderStatus.get();
            // OrderStatus의 orderStatusKeyId를 사용하여 KeyOrderStatus에서 KeyOrderStatus를 조회
            Long orderStatusKeyId = orderStatus.getOrderStatusKeyId();
            Optional<KeyOrderStatus> optionalKeyOrderStatus = keyOrderStatusRepository.findById(orderStatusKeyId);
            if (optionalKeyOrderStatus.isPresent()) {
                KeyOrderStatus keyOrderStatus = optionalKeyOrderStatus.get();
                // OrderDto에 설정
                orderDto.setOrderStatus(keyOrderStatus.getOrderStatus());
            }
        }

        return orderDto;
    }
}
