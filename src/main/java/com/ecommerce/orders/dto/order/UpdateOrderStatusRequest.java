package com.ecommerce.orders.dto.order;

import com.ecommerce.orders.type.OrderStatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request object for updating the status of an order")
public class UpdateOrderStatusRequest {

  @NotNull(message = "Status is required")
  @Schema(description = "New status of the order", example = "SHIPPED", requiredMode = Schema.RequiredMode.REQUIRED)
  private OrderStatusType status;

}