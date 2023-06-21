package com.shopshoe.service.impl;

import com.shopshoe.beans.Order;
import com.shopshoe.beans.OrderDetail;
import com.shopshoe.repository.OrderDetailRepository;
import com.shopshoe.repository.OrderRepository;
import com.shopshoe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order"));
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(Long id) {
        return orderDetailRepository.findByOrderId(id);
    }

    @Override
    public List<Order> getOrderByUserId(Long id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(id);
        for(OrderDetail orderDetail : orderDetailList){
            orderDetailRepository.deleteById(orderDetail.getId());
        }
    }
}
