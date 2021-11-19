package com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.web.rest.controller;

import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource.Order;
import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource.OrderExecuted;
import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("trade")
public class OrderContoller {

  @Autowired
  private OrderService orderService;

  @PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<OrderExecuted> placeOrderList(@Valid @RequestBody List<Order> order) {
    return this.orderService.insertOrders(order);
  }

  @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public boolean placeOrder(@Valid @RequestBody Order order) {
    try {
      this.orderService.insertOrder(order);
      return true;
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
  }

}
