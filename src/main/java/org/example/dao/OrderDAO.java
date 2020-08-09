package org.example.dao;

import org.example.model.Item;
import org.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    List<Order> findByItemAndPrice(Item item, BigDecimal price);
}
