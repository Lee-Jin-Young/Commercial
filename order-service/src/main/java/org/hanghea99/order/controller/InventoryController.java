package org.hanghea99.order.controller;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.dto.InitializeInventoryRequest;
import org.hanghea99.order.dto.SimulateAbandonmentRequest;
import org.hanghea99.order.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeInventory(@RequestBody InitializeInventoryRequest request) {
        inventoryService.initializeInventory(request.getProductId(), request.getInitialStock());
        return ResponseEntity.ok("Inventory initialized successfully.");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Integer> getInventory(@PathVariable("productId") int productId) {
        int inventory = inventoryService.getInventory(productId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/simulateAbandonment")
    public ResponseEntity<String> simulateAbandonment(@RequestBody SimulateAbandonmentRequest request) {
        inventoryService.simulateAbandonment(request.getProductId());
        return ResponseEntity.ok("Abandonment simulated successfully.");
    }
}
