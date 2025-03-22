package com.ecommerce.orders.service.external;

import com.ecommerce.orders.exception.order.InvalidOrderException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommerceToolsService {

  private final Map<Long, BigDecimal> productPriceMap = new HashMap<>();
  private final Map<Long, Integer> productStockMap = new HashMap<>();

  @PostConstruct
  public void initializeProductData() {

    // Inicialización de precios
    productPriceMap.put(1001L, new BigDecimal("250.00"));
    productPriceMap.put(1002L, new BigDecimal("199.99"));
    productPriceMap.put(1003L, new BigDecimal("549.50"));
    productPriceMap.put(1004L, new BigDecimal("129.90"));
    productPriceMap.put(1005L, new BigDecimal("75.00"));

    // Inicialización de stock
    productStockMap.put(1001L, 10);
    productStockMap.put(1002L, 5);
    productStockMap.put(1003L, 20);
    productStockMap.put(1004L, 15);
    productStockMap.put(1005L, 8);
  }

  /**
   * Obtiene el precio del producto dado su ID.
   * Lanza excepción si el producto no existe.
   */
  public BigDecimal getProductPrice(Long productId) {
    BigDecimal price = productPriceMap.get(productId);
    if (price == null) {
      throw new InvalidOrderException("Product ID " + productId + " not found or unavailable.");
    }
    return price;
  }

  /**
   * Obtiene la cantidad disponible de un producto dado su ID.
   * Lanza excepción si el producto no existe.
   */
  public int getAvailableQuantity(Long productId) {
    Integer quantity = productStockMap.get(productId);
    if (quantity == null) {
      throw new InvalidOrderException("Product ID " + productId + " not found or unavailable.");
    }
    return quantity;
  }

}