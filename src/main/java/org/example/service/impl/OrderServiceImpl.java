package org.example.service.impl;

import org.example.dao.OrderDAO;
import org.example.model.Item;
import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public List<Order> findByItemAndPrice(Item item, BigDecimal price) {
        return orderDAO.findByItemAndPrice(item, price);
    }

    public List<Order> getAllSellersWithOpenStatus(){
        List<Order> allBids = new ArrayList<>();
        for (Order order : findAll()) {
            if (order.getStatus().equals("open")){
                if (order.getOffer().equals("bid")){
                    allBids.add(order);
                }
            }
        }
        return allBids;
    }

    public List<Order> getAllBuyersWithOpenStatus(){
        List<Order> allAsks = new ArrayList<>();
        for (Order order : findAll()) {
            if (order.getStatus().equals("open")){
                if (order.getOffer().equals("ask")){
                    allAsks.add(order);
                }
            }
        }
        return allAsks;
    }

    public List<Order> sortedAllSellersWithOpenStatusByPrice(){
        // сортировка getAllSellersWithOpenStatus по убыванию
    }

    public List<Order> sortedAllBuyersWithOpenStatusByPrice(){
        // сортировка getAllBuyersWithOpenStatus по убыванию
    }


}
