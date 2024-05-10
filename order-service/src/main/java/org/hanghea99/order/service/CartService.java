package org.hanghea99.order.service;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.client.ProductClient;
import org.hanghea99.order.domain.Cart;
import org.hanghea99.order.dto.CartDto;
import org.hanghea99.order.dto.ProductDto;
import org.hanghea99.order.dto.UpdateCartDto;
import org.hanghea99.order.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final ProductClient productClient;

    public Object getInfo(Long userId) {
        // userId에 대한 카트 정보 가져오기
        List<Cart> carts = cartRepository.findAllByUserId(userId);

        // 카트 정보를 필요한 형식으로 가공하여 반환하기
        return carts.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }

    private CartDto mapToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        // ProductServiceFeignClient를 통해 Product 정보 호출하여 설정
        ProductDto productDto = productClient.getProductById(cart.getProductId());
        cartDto.setProductId(cart.getProductId());
        cartDto.setTitle(productDto.getTitle());
        cartDto.setQuantity(cart.getQuantity());
        cartDto.setPrice(productDto.getPrice());
        return cartDto;
    }

    public void updateCart(Long userId, List<UpdateCartDto> updateCartDtos) {
        // userId에 대한 카트 정보 가져오기
        List<Cart> carts = cartRepository.findAllByUserId(userId);

        // 주문 정보 업데이트 (카트 내 상품의 수량 변경)
        for (UpdateCartDto dto : updateCartDtos) {
            Long productId = dto.getProductId();
            Long quantity = dto.getQuantity();

            // 카트 리스트 중 productId가 동일한 값이 있는지 확인하고 수량 업데이트 또는 추가
            Optional<Cart> cart = cartRepository.findByProductId(productId);

            if (cart.isPresent()) {
                // 상품이 이미 카트에 있는 경우
                Cart cartItem = cart.get();

                if (quantity > 0) {
                    // 수량이 0보다 크면 수량 업데이트
                    cartItem.setQuantity(quantity);
                    cartRepository.save(cartItem);
                } else {
                    // 수량이 0이면 해당 상품을 카트에서 삭제
                    cartRepository.delete(cartItem);
                }
            } else {
                // 카트에 해당 상품이 없는 경우 데이터 추가
                Cart newCartItem = Cart.builder()
                        .userId(userId)
                        .productId(productId)
                        .quantity(quantity)
                        .build();
                cartRepository.save(newCartItem);
            }
        }
    }

    public Object deleteCart(Long userId, Long productId) {
        // product-service의 클라이언트를 사용하여 productId에 대한 상품 정보 가져오기
        // 카트에서 상품 제거
        return 0;
    }
}
