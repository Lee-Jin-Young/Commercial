package org.hanghea99.order.dto;

import lombok.Data;

@Data
public class StartPaymentRequest {
    private int productId;
    private int userId;
}
