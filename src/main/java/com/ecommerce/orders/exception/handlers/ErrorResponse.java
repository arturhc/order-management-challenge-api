package com.ecommerce.orders.exception.handlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents a structured error response returned by the API.
 */
@Data
@AllArgsConstructor
@Schema(description = "Structured error response returned by the API")
public class ErrorResponse {

  @Schema(description = "HTTP status code of the error", example = "400")
  private int status;

  @Schema(description = "Error name (e.g., Bad Request, Not Found)", example = "Bad Request")
  private String error;

  @Schema(description = "Detailed error message", example = "Invalid value for enum OrderStatusType")
  private String message;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @Schema(description = "Timestamp when the error occurred", example = "2025-03-22T14:25:00")
  private LocalDateTime timestamp;
}