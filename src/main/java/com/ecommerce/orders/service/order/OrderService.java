package com.ecommerce.orders.service.order;

import com.ecommerce.orders.dto.order.CreateOrderRequest;
import com.ecommerce.orders.dto.order.OrderResponse;
import com.ecommerce.orders.dto.order.UpdateOrderStatusRequest;

import java.util.List;

/**
 * Service interface for managing orders in the eCommerce system.
 * Handles business logic for placing, retrieving, updating, and listing orders.
 */
public interface OrderService {

  /**
   * Places a new order with the provided details.
   *
   * @param request the order creation request
   * @return the created order response
   */
  OrderResponse placeOrder(CreateOrderRequest request);

  /**
   * Retrieves an order by its ID.
   *
   * @param orderId the ID of the order
   * @return the order response
   */
  OrderResponse getOrderById(Long orderId);

  /**
   * Updates the status of an existing order.
   *
   * @param orderId the ID of the order to update
   * @param request the status update request
   * @return the updated order response
   */
  OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);

  /**
   * Retrieves all orders made by a specific customer.
   *
   * @param customerId the ID of the customer
   * @return a list of order responses
   */
  List<OrderResponse> getOrdersByCustomerId(Long customerId);

}