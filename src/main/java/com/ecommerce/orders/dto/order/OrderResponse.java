package com.ecommerce.orders.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "Represents an order response with full order details")
public class OrderResponse implements Serializable {

  @Schema(description = "Unique identifier of the order", example = "10001")
  private Long id;

  @Schema(description = "ID of the customer who placed the order", example = "123")
  private Long customerId;

  @Schema(description = "ID of the ordered product", example = "1001")
  private Long productId;

  @Schema(description = "Quantity of the product ordered", example = "2")
  private Integer quantity;

  @Schema(description = "Total price for the ordered quantity", example = "499.98")
  private BigDecimal price;

  @Schema(description = "Current status of the order", example = "SHIPPED")
  private String status;

  @Schema(description = "Timestamp when the order was created", example = "2025-03-21T10:15:30")
  private LocalDateTime createdAt;

  @Schema(description = "Timestamp when the order was last updated", example = "2025-03-22T08:45:00")
  private LocalDateTime updatedAt;
}