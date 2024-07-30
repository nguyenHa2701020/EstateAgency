package com.edu.estate_agency.service;

import com.edu.estate_agency.entity.Order;
import com.edu.estate_agency.model.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {
    Order saveOrders(CreateOrderRequest createOrderRequest);
    List<Order> getOrderCustomerss(Long id);
    List<Order> getOrderRooms(Long id);
    Order updateBrowse(Long id, String browse);
}
