package com.shopshoe.service;

import com.shopshoe.beans.Order;
import com.shopshoe.beans.OrderDetail;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    void saveOrderDetail(OrderDetail orderDetail);

    List<Order> getAllOrder();
    Order getOrderById(Long id);
    List<OrderDetail> getOrderDetailByOrderId(Long id);
    List<Order> getOrderByUserId(Long id);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
}
