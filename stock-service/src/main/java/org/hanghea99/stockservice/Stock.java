package org.hanghea99.stockservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Long stockId;
    private Long productId;
    private Long stock;

}
