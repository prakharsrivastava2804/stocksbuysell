package com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderExecuted {
  private String id;
  private int amount;
  private int quantity;
  private String share;
  private int buyerId;
  private int sellerId;
  private String buyOrderId;
  private String sellOrderId;
}
