package org.example.dao;

import org.example.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeDAO extends JpaRepository<Trade, Integer> {

}
