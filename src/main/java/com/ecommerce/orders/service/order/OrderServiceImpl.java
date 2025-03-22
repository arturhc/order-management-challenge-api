package com.ecommerce.orders.service.order;

import com.ecommerce.orders.dto.order.CreateOrderRequest;
import com.ecommerce.orders.dto.order.OrderResponse;
import com.ecommerce.orders.dto.order.UpdateOrderStatusRequest;
import com.ecommerce.orders.exception.order.InvalidOrderException;
import com.ecommerce.orders.exception.order.OrderNotFoundException;
import com.ecommerce.orders.model.Order;
import com.ecommerce.orders.repository.OrderRepository;
import com.ecommerce.orders.service.external.CommerceToolsService;
import com.ecommerce.orders.type.OrderStatusType;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of OrderService that handles order operations such as placement,
 * retrieval, status updates and listing customer orders.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  /** Repository for accessing and persisting Order entities */
  private final OrderRepository orderRepository;

  /** Service for retrieving product information from Commerce Tools */
  private final CommerceToolsService commerceToolsService;

  /** Utility for mapping between entities and DTOs */
  private final ModelMapper modelMapper;

  /**
   * Retrieves a single order by ID, using cache if available.
   */
  @Override
  @Cacheable(value = "orders", key = "#orderId")
  public OrderResponse getOrderById(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException(orderId));
    return mapToResponse(order);
  }

  /**
   * Places a new order after validating product availability.
   */
  @Override
  @Transactional
  @Caching(evict = {
      @CacheEvict(value = "customer_orders", key = "#result.customerId")
  })
  public OrderResponse placeOrder(CreateOrderRequest request) {

    validateProductAvailability(request.getProductId(), request.getQuantity());

    BigDecimal productPrice = commerceToolsService.getProductPrice(request.getProductId());

    Order order = new Order();
    order.setCustomerId(request.getCustomerId());
    order.setProductId(request.getProductId());
    order.setQuantity(request.getQuantity());
    order.setPrice(productPrice.multiply(BigDecimal.valueOf(request.getQuantity())));
    order.setStatus(OrderStatusType.PENDING);

    orderRepository.save(order);
    return mapToResponse(order);
  }

  /**
   * Updates the status of an existing order.
   */
  @Override
  @Transactional
  @Caching(evict = {
      @CacheEvict(value = "orders", key = "#orderId"),
      @CacheEvict(value = "customer_orders", key = "#result.customerId")
  })
  public OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {

    final OrderStatusType status = request.getStatus();

    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException(orderId));

    order.setStatus(status);

    orderRepository.save(order);

    return mapToResponse(order);
  }

  /**
   * Retrieves all orders associated with a given customer ID.
   */
  @Override
  @Cacheable(value = "customer_orders", key = "#customerId")
  public List<OrderResponse> getOrdersByCustomerId(Long customerId) {

    List<Order> orders = orderRepository.findByCustomerId(customerId);

    return orders.stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
  }

  /**
   * Validates that the requested product quantity is available.
   */
  private void validateProductAvailability(Long productId, int requestedQuantity) {

    int availableQuantity = commerceToolsService.getAvailableQuantity(productId);

    if (availableQuantity < requestedQuantity) {
      throw new InvalidOrderException("Insufficient stock for product ID: " + productId);
    }
  }

  /**
   * Maps an Order entity to an OrderResponse DTO.
   */
  private OrderResponse mapToResponse(Order order) {
    return modelMapper.map(order, OrderResponse.class);
  }

}
