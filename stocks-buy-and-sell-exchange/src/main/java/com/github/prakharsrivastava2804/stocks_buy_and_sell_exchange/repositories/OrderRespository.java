package com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.repositories;

import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource.Order;
import com.github.prakharsrivastava2804.stocks_buy_and_sell_exchange.resource.OrderExecuted;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRespository {
  private static List<Order> orderList = new ArrayList<Order>();
  private static List<OrderExecuted> executedOrders = new ArrayList<OrderExecuted>();

  public List<OrderExecuted> insertOrders(List<Order> orders) {
    try {
      for (Order order : orders) {
        this.insertOrder(order);
      }
      return OrderRespository.executedOrders;
    } catch (Exception e) {
      return new ArrayList<OrderExecuted>();
    }
  }

  public void insertOrder(Order order) {
    try {
      String uniqueID = UUID.randomUUID().toString();
      order.setId(uniqueID);
      OrderRespository.orderList.add(order);
      matchOrder(order);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public void matchOrder(Order orderPlaced) {
    for (Order order : OrderRespository.orderList) {
      if ((!(order.getId().equals(orderPlaced.getId()))) && (order.isBuy() != orderPlaced.isBuy())
          && orderPlaced.getShare().equals(order.getShare()) && (orderPlaced.getAmount() == order.getAmount())) {
        Order buyerOrder = null;
        Order sellerOrder = null;
        if (orderPlaced.isBuy()) {
          buyerOrder = orderPlaced;
          sellerOrder = order;
        } else {
          buyerOrder = order;
          sellerOrder = orderPlaced;
        }
        OrderExecuted executedOrder =
            OrderExecuted.builder().buyerId(buyerOrder.getUserId()).sellerId(sellerOrder.getUserId())
                .amount(orderPlaced.getAmount()).quantity(Math.min(buyerOrder.getQuantity(), sellerOrder.getQuantity()))
                .share(buyerOrder.getShare()).buyOrderId(buyerOrder.getId()).sellOrderId(sellerOrder.getId()).build();
        executedOrder.setId(UUID.randomUUID().toString());
        OrderRespository.executedOrders.add(executedOrder);
        this.reduceOrderQuantityFromQueue(buyerOrder.getId(), executedOrder.getQuantity());
        this.reduceOrderQuantityFromQueue(sellerOrder.getId(), executedOrder.getQuantity());
      }
    }
  }

  private void reduceOrderQuantityFromQueue(String id, int quantity) {
    for (Order order : OrderRespository.orderList) {
      if (order.getId() == id) {
        if (order.getQuantity() - quantity == 0) {
          this.removeOrderFromQueue(order.getId());
        } else {
          order.setQuantity(order.getQuantity() - quantity);
        }
      }
    }
  }

  private void removeOrderFromQueue(String id) {
    Iterator<Order> itr = OrderRespository.orderList.iterator();
    while (itr.hasNext()) {
      Order order = itr.next();
      if (order.getId() == id) {
        OrderRespository.orderList.remove(order);
      }
    }
  }

}
