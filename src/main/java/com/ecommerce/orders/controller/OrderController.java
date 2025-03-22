package com.ecommerce.orders.controller;

import com.ecommerce.orders.dto.order.CreateOrderRequest;
import com.ecommerce.orders.dto.order.OrderResponse;
import com.ecommerce.orders.dto.order.UpdateOrderStatusRequest;
import com.ecommerce.orders.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Operations related to order management")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  // Solo usuarios con rol USER pueden crear órdenes
  @PreAuthorize("hasRole('USER')")
  @Operation(summary = "Place a new order", description = "Creates a new order and returns its details")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Order created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid order request"),
      @ApiResponse(responseCode = "404", description = "Product not found")
  })
  @PostMapping
  public ResponseEntity<OrderResponse> placeOrder(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order details", required = true)
      @Valid @RequestBody CreateOrderRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(orderService.placeOrder(request));
  }

  // ADMIN o USER pueden consultar una orden
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  @Operation(summary = "Get an order by ID", description = "Retrieve details of a specific order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Order found"),
      @ApiResponse(responseCode = "404", description = "Order not found")
  })
  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponse> getOrder(
      @Parameter(description = "ID of the order", required = true)
      @PathVariable Long orderId) {
    return ResponseEntity.ok(orderService.getOrderById(orderId));
  }

  // ADMIN o MODERATOR pueden actualizar una orden
  @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
  @Operation(summary = "Update order status", description = "Updates the status of an existing order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Order status updated"),
      @ApiResponse(responseCode = "400", description = "Invalid status provided"),
      @ApiResponse(responseCode = "404", description = "Order not found")
  })
  @PatchMapping("/{orderId}/status")
  public ResponseEntity<OrderResponse> updateStatus(
      @Parameter(description = "ID of the order to update", required = true)
      @PathVariable Long orderId,
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New order status", required = true)
      @Valid @RequestBody UpdateOrderStatusRequest request) {
    return ResponseEntity.ok(orderService.updateOrderStatus(orderId, request));
  }

  // ADMIN o USER pueden ver las órdenes por cliente
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  @Operation(summary = "Get orders by customer ID", description = "Retrieve all orders placed by a specific customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
      @ApiResponse(responseCode = "404", description = "Customer not found")
  })
  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(
      @Parameter(description = "ID of the customer", required = true)
      @PathVariable Long customerId) {
    List<OrderResponse> orders = orderService.getOrdersByCustomerId(customerId);
    return ResponseEntity.ok(orders);
  }

}
