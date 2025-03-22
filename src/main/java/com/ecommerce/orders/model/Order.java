package com.ecommerce.orders.model;

import com.ecommerce.orders.model.common.BaseEntity;
import com.ecommerce.orders.type.OrderStatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(
    name = "orders",
    indexes = {
        @Index(name = "idx_customer_id", columnList = "customerId"),
        @Index(name = "idx_product_id", columnList = "productId"),
        @Index(name = "idx_status", columnList = "status")
    }
)
@Getter @Setter
@NoArgsConstructor

public class Order extends BaseEntity {

  private Long customerId;
  private Long productId;
  private Integer quantity;
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  private OrderStatusType status;

  @Column(length = 255)
  private String note; // Creada para probar Flyway

}