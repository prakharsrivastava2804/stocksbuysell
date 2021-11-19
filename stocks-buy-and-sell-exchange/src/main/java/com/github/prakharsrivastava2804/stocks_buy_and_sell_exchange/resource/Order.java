package com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private String id;
  private int userId;
  private int amount;
  private int quantity;
  private String share;
  private boolean buy;
  private int timestamp;
}
