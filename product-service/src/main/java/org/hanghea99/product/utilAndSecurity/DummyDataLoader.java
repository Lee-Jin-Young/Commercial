package org.hanghea99.product.utilAndSecurity;
import org.hanghea99.product.domain.KeyOrderType;
import org.hanghea99.product.domain.Product;
import org.hanghea99.product.domain.ProductDetail;
import org.hanghea99.product.repository.KeyOrderTypeRepository;
import org.hanghea99.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DummyDataLoader implements CommandLineRunner {

    private final KeyOrderTypeRepository keyOrderTypeRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DummyDataLoader(KeyOrderTypeRepository keyOrderTypeRepository, ProductRepository productRepository) {
        this.keyOrderTypeRepository = keyOrderTypeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // Create KeyOrderType
        KeyOrderType normalOrderType = KeyOrderType.builder().orderType("일반").build();
        KeyOrderType reservationOrderType = KeyOrderType.builder().orderType("예약주문").build();
        keyOrderTypeRepository.save(normalOrderType);
        keyOrderTypeRepository.save(reservationOrderType);

        // Create 10 Products with ProductDetails
        for (int i = 1; i <= 10; i++) {
            Product product = Product.builder()
                    .title("Product " + i)
                    .price(10000L + i * 1000L)
                    .keyOrderType(i % 2 == 0 ? normalOrderType : reservationOrderType)
                    .build();

            ProductDetail productDetail = ProductDetail.builder()
                    .description("Description for Product " + i)
                    .size("Size " + i)
                    .color(i % 2 == 0 ? "Blue" : "Red")
                    .openDateTime(LocalDateTime.now().minusDays(i))
                    .product(product)
                    .build();

            product.setDetail(productDetail);
            productRepository.save(product);
        }
    }
}

