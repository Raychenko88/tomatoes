package org.example.service.impl;

import org.example.dao.OrderDAO;
import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;


    @Override
    public Order save(Order order) throws Exception {
        if (order.getId() != null && orderDAO.findById(order.getId()) != null){
            throw new Exception("order with this id already exists");
        }
        return orderDAO.save(order);
    }

    @Override
    public Order update(Order order) throws Exception {
     if (order.getId() == null){
         throw new Exception("order id not found");
     }
     return orderDAO.save(order);
    }

    @Override
    public Order findById(Integer id) throws Exception {
        return orderDAO.findById(id).orElseThrow(() -> new Exception("order not found"));
    }

    @Override
    public void delete(Order order) {
        orderDAO.delete(order);
    }
}
