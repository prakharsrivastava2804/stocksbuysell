package com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.service.impl;

import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.repositories.OrderRespository;
import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource.Order;
import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource.OrderExecuted;
import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRespository orderRepo;

  public void insertOrder(Order order) {
    this.orderRepo.insertOrder(order);
  }

  public List<OrderExecuted> insertOrders(List<Order> orders) {
    return this.orderRepo.insertOrders(orders);
  }

}
