package com.ecommerce.orders.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request object for creating a new order")
public class CreateOrderRequest {

  @NotNull(message = "Customer ID is required")
  @Min(value = 1, message = "Customer ID must be greater than 0")
  @Schema(description = "ID of the customer placing the order", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long customerId;

  @NotNull(message = "Product ID is required")
  @Min(value = 1, message = "Product ID must be greater than 0")
  @Schema(description = "ID of the product to be ordered", example = "1001", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long productId;

  @NotNull(message = "Quantity is required")
  @Min(value = 1, message = "Quantity must be at least 1")
  @Schema(description = "Quantity of the product to order", example = "2", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
  private Integer quantity;

}