package com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.service;

import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource.Order;
import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource.OrderExecuted;

import java.util.List;

public interface OrderService {
  void insertOrder(Order order);

  List<OrderExecuted> insertOrders(List<Order> orders);
}
