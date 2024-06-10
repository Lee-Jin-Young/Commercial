package org.hanghea99.order.service;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.client.ProductClient;
import org.hanghea99.order.entity.KeyOrderStatus;
import org.hanghea99.order.entity.Order;
import org.hanghea99.order.entity.OrderStatus;
import org.hanghea99.order.dto.OrderDto;
import org.hanghea99.order.dto.ProductDto;
import org.hanghea99.order.repository.KeyOrderStatusRepository;
import org.hanghea99.order.repository.OrderRepository;
import org.hanghea99.order.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
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
        orderDto.setOrderId(order.getOrderId());
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
                orderDto.setKeyOrderStatus(keyOrderStatus.getStatus());
            }
        }

        return orderDto;
    }

    public void cancelOrder(Long orderId) {
        // OrderStatus에서 isNow가 true이고 orderId와 일치하는 OrderStatus를 조회
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findByIsNowAndOrderId(true, orderId);
        if (optionalOrderStatus.isPresent()) {
            OrderStatus orderStatus = optionalOrderStatus.get();
            // OrderStatus의 orderStatusKeyId를 사용하여 KeyOrderStatus에서 KeyOrderStatus를 조회
            Long keyOrderStatusId = orderStatus.getOrderStatusKeyId();
            KeyOrderStatus keyOrderStatus = keyOrderStatusRepository.findByKeyOrderStatusId(keyOrderStatusId);

            // 취소 불가능인 경우
            if (keyOrderStatus.getStatus().equals("배송 중")) {
                throw new IllegalArgumentException("이미 배송 중인 주문은 취소할 수 없습니다.");
            } else if (keyOrderStatus.getStatus().equals("배송 완료")) {
                throw new IllegalArgumentException("배송 완료 된 주문은 취소할 수 없습니다.");
            } else if (keyOrderStatus.getStatus().equals("주문 취소")) {
                throw new IllegalArgumentException("이미 주문 취소 중인 상품입니다.");
            }

            // TODO : 재고 수량 수정
            Long key = keyOrderStatusRepository.findIdByStatus("주문 취소");
            // 기존 상태 내역 수정
            orderStatus.setIsNow(false);
            orderStatusRepository.save(orderStatus);

            // 새로운 상태 내역 추가
            OrderStatus newOrderStatus = OrderStatus.builder()
                    .regdate(LocalDateTime.now())
                    .isNow(true)
                    .orderStatusKeyId(key)
                    .orderId(orderStatus.getOrderId())
                    .build();
            orderStatusRepository.save(newOrderStatus);
        } else {
            throw new IllegalArgumentException("주문을 찾을 수 없습니다.");
        }
    }

    public void returnProduct(Long orderId) {
        // OrderStatus에서 isNow가 true이고 orderId와 일치하는 OrderStatus를 조회
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findByIsNowAndOrderId(true, orderId);
        if (optionalOrderStatus.isPresent()) {
            OrderStatus orderStatus = optionalOrderStatus.get();
            // OrderStatus의 orderStatusKeyId를 사용하여 KeyOrderStatus에서 KeyOrderStatus를 조회
            Long keyOrderStatusId = orderStatus.getOrderStatusKeyId();
            KeyOrderStatus keyOrderStatus = keyOrderStatusRepository.findByKeyOrderStatusId(keyOrderStatusId);

            // 취소 불가능인 경우
            if (keyOrderStatus.getStatus().equals("상품 준비 중") || keyOrderStatus.getStatus().equals("배송 중")) {
                throw new IllegalArgumentException("배송 완료 이후 반품이 가능 합니다.");
            } else if (keyOrderStatus.getStatus().equals("주문 취소")) {
                throw new IllegalArgumentException("주문 취소 중 인 상품입니다.");
            } else if (keyOrderStatus.getStatus().equals("반품 진행 중")) {
                throw new IllegalArgumentException("반품 진행 중 인 상품입니다.");
            } else if (keyOrderStatus.getStatus().equals("반품 완료")) {
                throw new IllegalArgumentException("이미 반품 된 상품입니다.");
            }

            Long key = keyOrderStatusRepository.findIdByStatus("반품 진행 중");
            // 기존 상태 내역 수정
            orderStatus.setIsNow(false);
            orderStatusRepository.save(orderStatus);

            // 새로운 상태 내역 추가
            OrderStatus newOrderStatus = OrderStatus.builder()
                    .regdate(LocalDateTime.now())
                    .isNow(true)
                    .orderStatusKeyId(key)
                    .orderId(orderStatus.getOrderId())
                    .build();
            orderStatusRepository.save(newOrderStatus);
        } else {
            throw new IllegalArgumentException("주문을 찾을 수 없습니다.");
        }
    }
}
