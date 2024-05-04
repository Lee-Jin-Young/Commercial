package org.hanghea99.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    // 프론트 작업 시 필요한 정보
    private Long id;

    // 보여 줄 정보
    private String title;
    private Long price;
    private Long stock;
    private String orderType;
}
