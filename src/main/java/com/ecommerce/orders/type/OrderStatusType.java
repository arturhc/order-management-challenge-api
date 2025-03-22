package com.ecommerce.orders.type;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing the possible statuses for an order.
 */
@Schema(description = "Possible statuses for an order")
public enum OrderStatusType {

  @Schema(description = "Order has been placed but not yet shipped")
  PENDING,

  @Schema(description = "Order has been shipped to the customer")
  SHIPPED,

  @Schema(description = "Order has been delivered to the customer")
  DELIVERED
}