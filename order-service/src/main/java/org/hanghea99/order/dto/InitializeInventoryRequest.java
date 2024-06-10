package org.hanghea99.order.dto;

import lombok.Data;

@Data
public class InitializeInventoryRequest {
    private int productId;
    private int initialStock;
}