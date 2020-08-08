package org.example.service;

import org.example.model.Order;

public interface OrderService {

    Order save(Order order) throws Exception;
    Order update(Order order) throws Exception;
    Order findById(Integer id) throws Exception;
    void delete(Order order);
}
