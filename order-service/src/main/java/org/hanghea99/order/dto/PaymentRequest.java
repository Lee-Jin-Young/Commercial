package org.hanghea99.order.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private int productId;
    private int orderId;
}
