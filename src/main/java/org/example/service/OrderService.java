package org.example.service;

import org.example.model.Item;
import org.example.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    Order save(Order order) throws Exception;
    Order update(Order order) throws Exception;
    Order findById(Integer id) throws Exception;
    void delete(Order order);
    List<Order> findAll();
    public List<Order> getAllSellersWithOpenStatus();
    public List<Order> getAllBuyersWithOpenStatus();
    List<Order> findByItemAndPrice(Item item, BigDecimal price);
}
